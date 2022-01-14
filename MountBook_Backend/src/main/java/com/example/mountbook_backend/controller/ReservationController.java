package com.example.mountbook_backend.controller;

import com.example.mountbook_backend.entity.Reservation;
import com.example.mountbook_backend.entity.Room;
import com.example.mountbook_backend.entity.Shelter;
import com.example.mountbook_backend.entity.User;
import com.example.mountbook_backend.payload.request.ReservationRequest;
import com.example.mountbook_backend.repository.ReservationRepository;
import com.example.mountbook_backend.repository.RoomRepository;
import com.example.mountbook_backend.repository.ShelterRepository;
import com.example.mountbook_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    RoomRepository roomRepository;

    @Autowired
    ShelterRepository shelterRepository;

    @PostMapping("/doReservation")
    public ResponseEntity addNewShelter(@RequestBody ReservationRequest reservationRequest) {

        Optional<User> user = userRepository.findById(reservationRequest.getUser());
        if (user.isEmpty())
            return new ResponseEntity("invalid user", HttpStatus.BAD_REQUEST);

        List<Room> rooms = new ArrayList<Room>();
        if (reservationRequest.getReservedRooms().isEmpty())
            return new ResponseEntity("room list is empty", HttpStatus.BAD_REQUEST);

        //get a shelter from the 1° room of the list to compare with the other
        Optional<Shelter> shelterRiferiment = shelterRepository.findById(roomRepository
                .findById(reservationRequest.getReservedRooms().get(0)).get().getShelter().getId());
        //iterate all the lit of rooms
        for (Long room : reservationRequest.getReservedRooms()) {
            //get rooms from id and check if it's exist
            Optional<Room> r = roomRepository.findById(room);
            if (r.isEmpty())
                return new ResponseEntity("invalid room", HttpStatus.BAD_REQUEST);
            //get shelter from rooms ad check that they are all from the same shelter
            Optional<Shelter> s = shelterRepository.findById(r.get().getShelter().getId());
            if (shelterRiferiment.get().getId() != s.get().getId())
                return new ResponseEntity("error: all the rooms must be of the same shelter", HttpStatus.BAD_REQUEST);

            rooms.add(r.get());
        }
        //check if there are at least 1 guest
        if (reservationRequest.getGuests() < 0)
            return new ResponseEntity("invalid guest number", HttpStatus.BAD_REQUEST);

        //check that's the date isn't before the current date and the date isn't before the shelter open date
        if (reservationRequest.getFirstDay() == null || reservationRequest.getFirstDay().before(new Date()) ||
            reservationRequest.getFirstDay().before(shelterRiferiment.get().getOpen()))
            return new ResponseEntity("invalid data", HttpStatus.BAD_REQUEST);

        //check that's the date isn't before the current date and the date isn't after the shelter close date
        if (reservationRequest.getLastDay() == null || reservationRequest.getLastDay().before(new Date())||
            reservationRequest.getLastDay().after(shelterRiferiment.get().getClose()))
            return new ResponseEntity("invalid data", HttpStatus.BAD_REQUEST);

        //check that's, for the selected date, the rooms are not reserved yet
        if (!reservationRepository.existsReservationByFirstDayAndLastDayAndReservedRooms(reservationRequest.getFirstDay(),
                                                                                        reservationRequest.getLastDay(),
                                                                                        reservationRequest.getReservedRooms()).isEmpty())
            return new ResponseEntity("some of the selected rooms are already reserved", HttpStatus.BAD_REQUEST);


        reservationRepository.save(new Reservation(user.get(), rooms, reservationRequest.getGuests(), reservationRequest.getFirstDay(), reservationRequest.getLastDay()));
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
