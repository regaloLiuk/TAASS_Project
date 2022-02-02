package com.example.mountbook_backend.controller;

import com.example.mountbook_backend.entity.Reservation;
import com.example.mountbook_backend.entity.Shelter;
import com.example.mountbook_backend.entity.User;
import com.example.mountbook_backend.payload.request.ReservationRequest;
import com.example.mountbook_backend.repository.ReservationRepository;
import com.example.mountbook_backend.repository.ShelterRepository;
import com.example.mountbook_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    ShelterRepository shelterRepository;

    @PostMapping("/doReservation")
    public ResponseEntity addNewShelter(@RequestBody ReservationRequest reservationRequest) {

        Optional<User> user = userRepository.findById(reservationRequest.getUser());
        if (user.isEmpty())
            return new ResponseEntity("invalid user", HttpStatus.BAD_REQUEST);

        Optional<Shelter> shelter = shelterRepository.findById(reservationRequest.getShelter());
        if (shelter.isEmpty())
            return new ResponseEntity("invalid shelter", HttpStatus.BAD_REQUEST);

        //check if there are at least 1 guest
        if (reservationRequest.getGuests() < 0)
            return new ResponseEntity("invalid guest number", HttpStatus.BAD_REQUEST);

        //check that's the date isn't before the current date and the date isn't before the shelter open date
        if (reservationRequest.getFirstDay() == null || reservationRequest.getFirstDay().before(new Date()) ||
            reservationRequest.getFirstDay().before(shelter.get().getOpen()))
            return new ResponseEntity("invalid data", HttpStatus.BAD_REQUEST);

        //check that's the date isn't before the current date and the date isn't after the shelter close date
        if (reservationRequest.getLastDay() == null || reservationRequest.getLastDay().before(new Date())||
            reservationRequest.getLastDay().after(shelter.get().getClose()))
            return new ResponseEntity("invalid data", HttpStatus.BAD_REQUEST);

        //check that's, for the selected date, the rooms are not reserved yet
        List<Reservation> reservationForShelter = reservationRepository.findReservationByDateAndShelter(reservationRequest.getFirstDay(),
                                                                                                        reservationRequest.getLastDay(),
                                                                                                        reservationRequest.getShelter());
        int bedCount = 0;
        for (Reservation r  : reservationForShelter){
            bedCount += r.getGuests();
        }
        if (bedCount+reservationRequest.getGuests() > shelter.get().getMaxNumBed())
            return new ResponseEntity("there's not enought space for reservation", HttpStatus.BAD_REQUEST);


        reservationRepository.save(new Reservation(user.get(), shelter.get(), reservationRequest.getGuests(), reservationRequest.getFirstDay(), reservationRequest.getLastDay()));
        return new ResponseEntity("reservation successfully evaluated", HttpStatus.OK);
    }

    @PostMapping("deleteReservation")
    public ResponseEntity deleteReservation(@RequestParam Long userId, @RequestParam Long reservationId) {

        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty())
            return new ResponseEntity<>("user not found", HttpStatus.BAD_REQUEST);

        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if (reservation.isEmpty())
            return new ResponseEntity<>("reservation not found", HttpStatus.BAD_REQUEST);

        //check if the reservation date is before the current date
        if (reservation.get().getFirstDay().before(new Date()))
            return new ResponseEntity<>("you can't delete this reservation because you have already do it", HttpStatus.BAD_REQUEST);


        reservationRepository.delete(reservation.get());
        return new ResponseEntity<>("reservation successfully deleted", HttpStatus.OK);
    }
}
