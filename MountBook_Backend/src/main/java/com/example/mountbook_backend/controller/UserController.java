package com.example.mountbook_backend.controller;

import com.example.mountbook_backend.entity.User;
import com.example.mountbook_backend.payload.responce.MessageResponse;
import com.example.mountbook_backend.repository.UserRepository;
import com.example.mountbook_backend.security.jwt.JwtUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/getUsers")
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok(new MessageResponse("Users: " + userRepository.findAll()));}

    @GetMapping("/getUserById")
    public Optional<User> getUserById(Long id){return userRepository.findById(id);}

    @GetMapping("/getUserByEmail")
    public Optional<User> getUserByEmail(String email){return userRepository.findByEmail(email);}

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

    @PostMapping("user")
    public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) {

        String token = getJWTToken(username);
        User user = new User();
        user.setUsername(username);
        user.setToken(token);
        return user;

    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

}
