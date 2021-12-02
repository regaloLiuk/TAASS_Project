package com.example.mountbook_backend.controller;

import com.example.mountbook_backend.entity.Host;
import com.example.mountbook_backend.repository.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public class HostController {

    @Autowired
    private HostRepository hostRepository;

    @GetMapping("/getHosts")
    public List<Host> getHosts(){return hostRepository.findAll();}

    @GetMapping("/getHostById")
    public Optional<Host> getHostById(Long id){return hostRepository.findById(id);}

    @GetMapping("/getHostByEmail")
    public Optional<Host> getHostByEmail(String email){return hostRepository.findByEmail(email);}

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password){
        Optional<Host> host =  hostRepository.findByEmail(email);
        if (host.isEmpty())
            return new ResponseEntity<String>("no host found for this email", HttpStatus.NOT_FOUND);
        if (!host.get().getPassword().equals(password))
            return new ResponseEntity<String>("email or password are incorrect", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<String>("Access Approved", HttpStatus.OK);

    }
    
    @PostMapping("/singIn")
    public ResponseEntity<String> singIn(@RequestBody Host u){
        if(!getHostByEmail(u.getEmail()).isPresent())
            return new ResponseEntity<String>("this email is already used", HttpStatus.BAD_REQUEST);
        hostRepository.save(u);
        return new ResponseEntity<String>("new host registred", HttpStatus.OK);
    }

    @PostMapping("/changePaqssword")
    public ResponseEntity<String> changePassword(@RequestParam String mail, @RequestParam String oldPassword, @RequestParam String newPassword){
        Optional<Host> host = getHostByEmail(mail);
        if (!host.isPresent())
            return new ResponseEntity<String>("no host found for this email", HttpStatus.NOT_FOUND);
        if (host.get().getPassword().equals(oldPassword))
            return new ResponseEntity<String>("the password privided is incorrect", HttpStatus.BAD_REQUEST);
        if (oldPassword.equals(newPassword))
            return new ResponseEntity<String>("the new password must be different from the old one", HttpStatus.BAD_REQUEST);

        hostRepository.updatePassword(mail,newPassword);
        return new ResponseEntity<String>("password changed successfully", HttpStatus.OK);
    }
    
}
