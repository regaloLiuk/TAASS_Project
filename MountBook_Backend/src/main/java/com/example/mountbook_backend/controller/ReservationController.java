package com.example.mountbook_backend.controller;

import com.example.mountbook_backend.entity.Reservation;
import com.example.mountbook_backend.entity.Room;
import com.example.mountbook_backend.entity.User;
import com.example.mountbook_backend.payload.request.ReservationRequest;
import com.example.mountbook_backend.payload.request.ShelterRequest;
import com.example.mountbook_backend.repository.ReservationRepository;
import com.example.mountbook_backend.repository.RoomRepository;
import com.example.mountbook_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomRepository roomRepository;

    @PostMapping("/reservation")
    public ResponseEntity addNewShelter(@RequestBody ReservationRequest reservationRequest) {

        Optional<User> user = userRepository.findById(reservationRequest.getUser());
        if (user.isEmpty())
            return new ResponseEntity("invalid user", HttpStatus.BAD_REQUEST);

        List<Room> rooms = new ArrayList<Room>();
        if (reservationRequest.getReservedRooms().isEmpty())
            return new ResponseEntity("room list is empty", HttpStatus.BAD_REQUEST);
        for (Long room : reservationRequest.getReservedRooms()){
            Optional<Room> r = roomRepository.findById(room);
            if (r.isEmpty())
                return new ResponseEntity("invalid room", HttpStatus.BAD_REQUEST);
            rooms.add(r.get());
        }

        if (reservationRequest.getGuests()<0)
            return new ResponseEntity("invalid guest number", HttpStatus.BAD_REQUEST);

        if (reservationRequest.getFirstDay() == null || reservationRequest.getFirstDay().before(new Date()))
            return new ResponseEntity("invalid data", HttpStatus.BAD_REQUEST);

        if (reservationRequest.getLastDay() == null || reservationRequest.getLastDay().before(new Date()))
            return new ResponseEntity("invalid data", HttpStatus.BAD_REQUEST);

        reservationRepository.save(new Reservation(user.get(), rooms, reservationRequest.getGuests(), reservationRequest.getFirstDay(), reservationRequest.getLastDay()));
        return new ResponseEntity("reservation successfully evaluated", HttpStatus.OK);

    }

}
