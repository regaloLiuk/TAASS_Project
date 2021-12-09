package com.example.mountbook_backend.controller;

import com.example.mountbook_backend.entity.Reservation;
import com.example.mountbook_backend.entity.Shelter;
import com.example.mountbook_backend.entity.User;
import com.example.mountbook_backend.repository.ReservationRepository;
import com.example.mountbook_backend.repository.ShelterRepository;
import com.example.mountbook_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ReservationController {

    @Autowired private UserRepository userRepository;
    @Autowired private ShelterRepository shelterRepository;
    @Autowired private ReservationRepository reservationRepository;

    @PostMapping("/reservation")
    public ResponseEntity<String> login(@RequestBody @NonNull Reservation request){
        Optional<Shelter> shelter =  shelterRepository.findById(request.getShelter().getId());
        Optional<User> user =  userRepository.findById(request.getUser().getId());
        if (user.isEmpty())
            return new ResponseEntity<String>("no user found", HttpStatus.NOT_FOUND);
        if (shelter.isEmpty())
            return new ResponseEntity<String>("no shelter found", HttpStatus.NOT_FOUND);
        if (shelter.get().getOpen().before(request.getStart()) || shelter.get().getOpen().after(request.getStart()))
            return new ResponseEntity<String>("the shelter is closed !!", HttpStatus.BAD_REQUEST);
        // reservationRepository.save(request);
        return new ResponseEntity<String>("Access Approved", HttpStatus.OK);
    }
}
