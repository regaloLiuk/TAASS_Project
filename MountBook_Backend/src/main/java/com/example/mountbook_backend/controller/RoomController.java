package com.example.mountbook_backend.controller;

import com.example.mountbook_backend.entity.Room;
import com.example.mountbook_backend.repository.RoomRepository;
import com.example.mountbook_backend.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    @Autowired
    RoomRepository roomRepository;
    @Autowired
    ShelterRepository shelterRepository;


//     }

}
