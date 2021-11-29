package com.example.mountbook_backend.controller;

import com.example.mountbook_backend.entity.User;
import com.example.mountbook_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController{
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getUsers")
    public List<User> getUsers(){return userRepository.findAll();}

    @GetMapping("/getUserById")
    public Optional<User> getUserById(Long id){return userRepository.findById(id);}

    @GetMapping("/getUserByEmail")
    public Optional<User> getUserByEmail(String email){return userRepository.findByEmail(email);}

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password){
        Optional<User> user =  userRepository.findByEmail(email);
        if (user.isEmpty())
            return new ResponseEntity<String>("no user found for this email", HttpStatus.NOT_FOUND);
        if (!user.get().getPassword().equals(password))
            return new ResponseEntity<String>("email or password are incorrect", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<String>("Access Approved", HttpStatus.OK);

    }

    @PostMapping("/singIn")
    public ResponseEntity<String> singIn(@RequestBody User u){
        if(!getUserByEmail(u.getEmail()).isPresent())
            return new ResponseEntity<String>("this email is already used", HttpStatus.BAD_REQUEST);
        userRepository.save(u);
        return new ResponseEntity<String>("new user registred", HttpStatus.OK);
    }

    @PostMapping("/changePaqssword")
    public ResponseEntity<String> changePassword(@RequestParam String mail, @RequestParam String oldPassword, @RequestParam String newPassword){
        Optional<User> user = getUserByEmail(mail);
        if (!user.isPresent())
            return new ResponseEntity<String>("no user found for this email", HttpStatus.NOT_FOUND);
        if (user.get().getPassword().equals(oldPassword))
            return new ResponseEntity<String>("the password privided is incorrect", HttpStatus.BAD_REQUEST);
        if (oldPassword.equals(newPassword))
            return new ResponseEntity<String>("the new password must be different from the old one", HttpStatus.BAD_REQUEST);

        userRepository.updatePassword(mail,newPassword);
        return new ResponseEntity<String>("password changed successfully", HttpStatus.OK);
    }

}
