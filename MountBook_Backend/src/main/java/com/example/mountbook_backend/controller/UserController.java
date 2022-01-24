package com.example.mountbook_backend.controller;

import com.example.mountbook_backend.entity.ERole;
import com.example.mountbook_backend.entity.Role;
import com.example.mountbook_backend.entity.User;
import com.example.mountbook_backend.payload.request.LoginRequest;
import com.example.mountbook_backend.payload.request.SignupRequest;
import com.example.mountbook_backend.repository.RoleRepository;
import com.example.mountbook_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/user")
public class UserController{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/signup")
    public ResponseEntity registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername()))
            return new ResponseEntity("Error: Username is already taken!", HttpStatus.BAD_REQUEST);
        if (userRepository.existsByEmail(signUpRequest.getEmail()))
            return new ResponseEntity("Error: Email is already in use!", HttpStatus.BAD_REQUEST);

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getPassword());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "moderator":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    case "host":
                        Role hostRole = roleRepository.findByName(ERole.ROLE_HOST)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(hostRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return new ResponseEntity("User registered successfully!",HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity changePassword(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
        if (user.isEmpty())
            return new ResponseEntity("username or password are incorrect", HttpStatus.BAD_REQUEST);
        if (!user.get().getPassword().equals(loginRequest.getPassword()))
            return new ResponseEntity("username or password are incorrect", HttpStatus.BAD_REQUEST);
        return new ResponseEntity("Welcome " + user.get().getUsername() + "your", HttpStatus.OK);
    }


    @PostMapping("/changePaqssword")
    public ResponseEntity changePassword(@RequestParam String mail, @RequestParam String oldPassword, @RequestParam String newPassword){
        Optional<User> user = userRepository.findByEmail(mail);
        if (!user.isPresent())
            return new ResponseEntity("no user found for this email", HttpStatus.NOT_FOUND);
        if (user.get().getPassword().equals(oldPassword))
            return new ResponseEntity("the password privided is incorrect", HttpStatus.BAD_REQUEST);
        if (oldPassword.equals(newPassword))
            return new ResponseEntity("the new password must be different from the old one", HttpStatus.BAD_REQUEST);

        userRepository.updatePassword(mail,newPassword);
        return new ResponseEntity<String>("password changed successfully", HttpStatus.OK);
    }
}
