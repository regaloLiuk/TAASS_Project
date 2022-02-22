package com.example.mountbook_backend.controller;

import com.example.mountbook_backend.entity.*;
import com.example.mountbook_backend.payload.request.CommentRequest;
import com.example.mountbook_backend.repository.CommentRepository;
import com.example.mountbook_backend.repository.ReservationRepository;
import com.example.mountbook_backend.repository.ShelterRepository;
import com.example.mountbook_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    @Autowired
    ShelterRepository shelterRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ReservationRepository reservationRepository;

    @PostMapping("/doComment")
    public ResponseEntity doComment(@RequestBody CommentRequest commentRequest){

        //check if the shelter exist
        Optional<Shelter> shelter = shelterRepository.findById(commentRequest.getShelter());
        if (shelter.isEmpty())
            return new ResponseEntity("no shelters found", HttpStatus.BAD_REQUEST);

        //check if the user exist
        Optional<User> user = userRepository.findById(commentRequest.getUser());
        if (user.isEmpty())
            return new ResponseEntity("no users found", HttpStatus.BAD_REQUEST);

        //check if the user have done the journey
        List<Reservation> userReservation = reservationRepository.findAllByUser(user.get());
        boolean haveUserDoneJourney = false;
        //ITERATE ALL RESERVATION OF A USER
        for (Reservation r : userReservation){
            if (r.getShelter().getId() == commentRequest.getShelter())
                haveUserDoneJourney=true;
        }
        if(!haveUserDoneJourney)
            return new ResponseEntity("user not have done the journey", HttpStatus.BAD_REQUEST);

        Comment comment = new Comment(user.get(), shelter.get());

        if (commentRequest.isClear())
            comment.setClear(true);

        if (commentRequest.isService())
            comment.setService(true);

        if (commentRequest.isOspitality())
            comment.setOspitality(true);

        commentRepository.save(comment);
        return new ResponseEntity("tanks for your valutation", HttpStatus.OK);


    }

}
