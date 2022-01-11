package com.example.mountbook_backend.controller;

import com.example.mountbook_backend.entity.Room;
import com.example.mountbook_backend.entity.Shelter;
import com.example.mountbook_backend.payload.request.LoginRequest;
import com.example.mountbook_backend.payload.request.ShelterRequest;
import com.example.mountbook_backend.repository.RoomRepository;
import com.example.mountbook_backend.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shelter")
public class ShelterController {

    @Autowired
    ShelterRepository shelterRepository;
    @Autowired
    RoomRepository roomRepository;




}
