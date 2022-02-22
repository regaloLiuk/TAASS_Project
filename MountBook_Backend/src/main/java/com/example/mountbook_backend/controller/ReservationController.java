package com.example.mountbook_backend.controller;

import com.example.mountbook_backend.entity.Reservation;
import com.example.mountbook_backend.entity.Shelter;
import com.example.mountbook_backend.entity.User;
import com.example.mountbook_backend.payload.request.ReservationDeleteRequest;
import com.example.mountbook_backend.payload.request.ReservationRequest;
import com.example.mountbook_backend.repository.ReservationRepository;
import com.example.mountbook_backend.repository.ShelterRepository;
import com.example.mountbook_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController implements Serializable {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShelterRepository shelterRepository;

    @GetMapping("/getReservationForUser")
    public ResponseEntity getReservationForUser(@RequestParam Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty())
            return new ResponseEntity("invalid user", HttpStatus.BAD_REQUEST);
        return new ResponseEntity(reservationRepository.findAllByUser(user.get()), HttpStatus.OK);
    }

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

        //check that's first day is before last day
        if (!reservationRequest.getFirstDay().before(reservationRequest.getLastDay()))
            return new ResponseEntity("invalid data: first day is after last day", HttpStatus.BAD_REQUEST);

        //check that's the date isn't before the current date and the date isn't before the shelter open date
        if (reservationRequest.getFirstDay() == null)
            return new ResponseEntity("invalid data: first day is null", HttpStatus.BAD_REQUEST);
        if (reservationRequest.getFirstDay().before(new Date()))
            return new ResponseEntity("invalid data: first day is before current data", HttpStatus.BAD_REQUEST);
        if (reservationRequest.getFirstDay().before(shelter.get().getOpen()))
            return new ResponseEntity("invalid data: first day is before shelter's open data", HttpStatus.BAD_REQUEST);

        //check that's the date isn't before the current date and the date isn't after the shelter close date
        if (reservationRequest.getLastDay() == null)
            return new ResponseEntity("invalid data: last day is null", HttpStatus.BAD_REQUEST);
        if (reservationRequest.getLastDay().before(new Date()))
            return new ResponseEntity("invalid data: last day is before current data", HttpStatus.BAD_REQUEST);
        if (reservationRequest.getLastDay().after(shelter.get().getClose()))
            return new ResponseEntity("invalid data: the shelter is closed for selected data", HttpStatus.BAD_REQUEST);

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


        Reservation res=reservationRepository.save(new Reservation(user.get(), shelter.get(), reservationRequest.getGuests(), reservationRequest.getFirstDay(), reservationRequest.getLastDay()));

        return new ResponseEntity(res, HttpStatus.OK);
    }

//    @PostMapping("deleteReservation")
//    public ResponseEntity deleteReservation(@RequestParam Long userId, @RequestParam Long reservationId) {
//
//        Optional<User> user = userRepository.findById(userId);
//        if (user.isEmpty())
//            return new ResponseEntity<>("user not found", HttpStatus.BAD_REQUEST);
//
//        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
//        if (reservation.isEmpty())
//            return new ResponseEntity<>("reservation not found", HttpStatus.BAD_REQUEST);
//
//        //check if the reservation date is before the current date
//        if (reservation.get().getFirstDay().before(new Date()))
//            return new ResponseEntity<>("you can't delete this reservation because you have already do it", HttpStatus.BAD_REQUEST);
//
//
//        reservationRepository.delete(reservation.get());
//        return new ResponseEntity<>("reservation successfully deleted", HttpStatus.OK);
//    }

    @PostMapping("deleteReservation")
    public ResponseEntity deleteReservation(@RequestBody ReservationDeleteRequest reservationDeleteRequest) {

        Optional<User> user = userRepository.findById(reservationDeleteRequest.getUserId());
        if (user.isEmpty())
            return new ResponseEntity<>("user not found", HttpStatus.BAD_REQUEST);

        Optional<Reservation> reservation = reservationRepository.findById(reservationDeleteRequest.getReservationId());
        if (reservation.isEmpty())
            return new ResponseEntity<>("reservation not found", HttpStatus.BAD_REQUEST);

        //check if the reservation date is before the current date
        if (reservation.get().getFirstDay().before(new Date()))
            return new ResponseEntity<>("you can't delete this reservation because you have already do it", HttpStatus.BAD_REQUEST);


        reservationRepository.delete(reservation.get());
        return new ResponseEntity<>("reservation successfully deleted", HttpStatus.OK);
    }
}
