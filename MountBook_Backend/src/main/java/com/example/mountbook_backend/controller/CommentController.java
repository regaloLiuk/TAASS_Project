package com.example.mountbook_backend.controller;

import com.example.mountbook_backend.entity.Comment;
import com.example.mountbook_backend.entity.Shelter;
import com.example.mountbook_backend.entity.User;
import com.example.mountbook_backend.payload.request.CommentRequest;
import com.example.mountbook_backend.repository.CommentRepository;
import com.example.mountbook_backend.repository.ShelterRepository;
import com.example.mountbook_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    ShelterRepository shelterRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;

    @PostMapping("newComment")
    public ResponseEntity doComment(@RequestBody CommentRequest commentRequest){
        Optional<Shelter> shelter = shelterRepository.findById(commentRequest.getShelter());
        if (shelter.isEmpty())
            return new ResponseEntity("no shelters found", HttpStatus.BAD_REQUEST);
        Optional<User> user = userRepository.findById(commentRequest.getUser());
        if (user.isEmpty())
            return new ResponseEntity("no users found", HttpStatus.BAD_REQUEST);

        Comment comment = new Comment(user.get(), shelter.get());

        if (commentRequest.isClear() || !comment.isClear())
            comment.setClear(commentRequest.isClear());

        if (commentRequest.isService() || !comment.isService())
            comment.setClear(commentRequest.isService());

        if (commentRequest.isOspitality() || !comment.isOspitality())
            comment.setClear(commentRequest.isOspitality());

        commentRepository.save(comment);
        return new ResponseEntity("tanks for your valutation", HttpStatus.OK);


    }

}
