package com.example.mountbook_backend.controller;

import com.example.mountbook_backend.entity.User;
import com.example.mountbook_backend.payload.responce.MessageResponse;
import com.example.mountbook_backend.payload.responce.UserHistoryResponse;
import com.example.mountbook_backend.payload.responce.UserMinimalResponse;
import com.example.mountbook_backend.repository.CommentRepository;
import com.example.mountbook_backend.repository.ReservationRepository;
import com.example.mountbook_backend.repository.RoleRepository;
import com.example.mountbook_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    /*@PostMapping("/signup")
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
    }*/

    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @PostMapping("/changePassword")
    public ResponseEntity changePassword(@RequestParam String username, @RequestParam String oldPassword, @RequestParam String newPassword) {

        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent())
            return new ResponseEntity("no user found for this email", HttpStatus.NOT_FOUND);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.get().getUsername(), oldPassword));
        if (!authentication.isAuthenticated())
            return ResponseEntity.badRequest().body(new MessageResponse("user must be authenticated to change password"));

        if (!encoder.matches(oldPassword, user.get().getPassword()))
            return new ResponseEntity("the password provided is incorrect", HttpStatus.BAD_REQUEST);
        if (oldPassword.equals(newPassword))
            return new ResponseEntity("the new password must be different from the old one", HttpStatus.BAD_REQUEST);

        userRepository.updatePassword(user.get().getEmail(), encoder.encode(newPassword));
        return new ResponseEntity<String>("password changed successfully", HttpStatus.OK);
    }

    @GetMapping("/getUserHistory")
    public ResponseEntity changePassword(@RequestParam Long userId) {
        Optional<UserMinimalResponse> user = userRepository.findUserById(userId);
        if (!user.isPresent())
            return new ResponseEntity("no user found for id: " + userId, HttpStatus.NOT_FOUND);
        return new ResponseEntity(new UserHistoryResponse(user.get(),
                                    reservationRepository.findAllReservationByUser(user.get().getId()),
                                    commentRepository.findAllByUser(user.get().getId())), HttpStatus.OK);
    }
}
