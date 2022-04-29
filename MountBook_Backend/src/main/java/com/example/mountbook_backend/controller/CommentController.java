package com.example.mountbook_backend.controller;

import com.example.mountbook_backend.entity.*;
import com.example.mountbook_backend.payload.request.CommentRequest;
import com.example.mountbook_backend.payload.responce.ReservationResponse;
import com.example.mountbook_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "", allowedHeaders = "")
@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    @Autowired
    ShelterRepository shelterRepository;
    @Autowired
    BivouacRepository bivouacRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ReservationRepository reservationRepository;

    @GetMapping("/getCommentOfUser")
    public ResponseEntity doComment(@RequestParam Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty())
            return new ResponseEntity("user not foud for id: " + userId, HttpStatus.BAD_REQUEST);
        return new ResponseEntity(commentRepository.findAllByUser(user.get().getId()), HttpStatus.OK);
    }

    @PostMapping("/doComment")
    public ResponseEntity doComment(@RequestBody CommentRequest commentRequest){

        //check if the user is not null and exists
        if (commentRequest.getUser()==null)
            return new ResponseEntity("users field is null", HttpStatus.BAD_REQUEST);
        Optional<User> user = userRepository.findById(commentRequest.getUser());
        if (user.isEmpty())
            return new ResponseEntity("no users found", HttpStatus.BAD_REQUEST);

        //check if the user have done the journey
        List<ReservationResponse> userReservation = reservationRepository.findAllReservationByUser(user.get().getId());
        boolean haveUserDoneJourney = false;
        //ITERATE ALL RESERVATION OF A USER
        for (ReservationResponse r : userReservation){
            if (r.getShelterId() == commentRequest.getShelter())
                haveUserDoneJourney=true;
        }
        if(!haveUserDoneJourney)
            return new ResponseEntity("you don't have done the journey", HttpStatus.BAD_REQUEST);

        Comment comment = new Comment(user.get());

        //verify that was selected only a shelter or a bivouac
        if (commentRequest.getShelter()!=null && commentRequest.getBivouac()!=null)
            return new ResponseEntity("chose one between shelter and bivouac", HttpStatus.BAD_REQUEST);
        //SHELTER
        if (commentRequest.getShelter()!=null){
            Optional<Shelter> shelter = shelterRepository.findById(commentRequest.getShelter());
            if (shelter.isEmpty())
                return new ResponseEntity("shelter not foud for id: " + commentRequest.getShelter(), HttpStatus.BAD_REQUEST);
            comment.setShelter(shelter.get());
        }
        if (commentRequest.getShelter()!=null){
            Optional<Bivouac> bivouac = bivouacRepository.findById(commentRequest.getBivouac());
            if (bivouac.isEmpty())
                return new ResponseEntity("you must specify a shelter or a bivouac", HttpStatus.BAD_REQUEST);
            comment.setBivouac(bivouac.get());
        }

        if (commentRequest.isClear())
            comment.setClear(true);

        if (commentRequest.isService())
            comment.setService(true);

        if (commentRequest.isOspitality())
            comment.setOspitality(true);

        if (commentRequest.isFood())
            comment.setFood(true);

        if (commentRequest.isLocation())
            comment.setLocation(true);

        commentRepository.save(comment);
        return new ResponseEntity("tanks for your valutation", HttpStatus.OK);
    }

}
