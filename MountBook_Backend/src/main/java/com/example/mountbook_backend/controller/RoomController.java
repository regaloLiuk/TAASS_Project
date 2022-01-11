package com.example.mountbook_backend.controller;

import com.example.mountbook_backend.entity.Reservation;
import com.example.mountbook_backend.entity.Room;
import com.example.mountbook_backend.entity.Shelter;
import com.example.mountbook_backend.payload.request.RoomRequest;
import com.example.mountbook_backend.payload.request.ShelterRequest;
import com.example.mountbook_backend.repository.RoomRepository;
import com.example.mountbook_backend.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.management.OperatingSystemMXBean;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    @Autowired
    RoomRepository roomRepository;
    @Autowired
    ShelterRepository shelterRepository;

    @PostMapping("newRoom")
    public ResponseEntity getFreeRoom(@RequestBody RoomRequest roomRequest){

//        if(roomRequest.getDescription() == null || roomRequest.getDescription().isEmpty())
//            return new ResponseEntity("request must have a description", HttpStatus.BAD_REQUEST);
//        if(roomRequest.getBeds() < 0)
//            return new ResponseEntity("request must have a bed", HttpStatus.BAD_REQUEST);
//        if(roomRequest.getPrice() < 0)
//            return new ResponseEntity("request must have a price", HttpStatus.BAD_REQUEST);
        Optional<Shelter> shelter = shelterRepository.findById(roomRequest.getShelterId());
        if (shelter.isEmpty())
            return new ResponseEntity("request must be associated to a valid shelter", HttpStatus.BAD_REQUEST);

        Room room = new Room(roomRequest.getDescription(), roomRequest.getBeds(), roomRequest.getPrice());
        room.setShelter(shelter.get());
        roomRepository.save(room);
        return new ResponseEntity("room successfully added", HttpStatus.OK);
    }

        @GetMapping("freeRoom")
    public ResponseEntity getFreeRoom(@RequestParam Long shelterId){
        List<Room> roomList = roomRepository.getRoomNotReserved(shelterId);
        if (roomList.isEmpty())
            return new ResponseEntity("no room are left in this shelter", HttpStatus.NOT_FOUND);
        return new ResponseEntity("rooms: " + roomList, HttpStatus.OK);
    }

}
