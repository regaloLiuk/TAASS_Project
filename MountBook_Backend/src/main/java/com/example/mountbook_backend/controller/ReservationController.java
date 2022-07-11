package com.example.mountbook_backend.controller;

import com.example.mountbook_backend.entity.Reservation;
import com.example.mountbook_backend.entity.Structure;
import com.example.mountbook_backend.entity.User;
import com.example.mountbook_backend.payload.request.ReservationDeleteRequest;
import com.example.mountbook_backend.payload.request.ReservationRequest;
import com.example.mountbook_backend.payload.responce.ReservationResponse;
import com.example.mountbook_backend.repository.ReservationRepository;
import com.example.mountbook_backend.repository.StructureRepository;
import com.example.mountbook_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "", allowedHeaders = "")
@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController implements Serializable {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StructureRepository structureRepository;


    @GetMapping("/getReservationForUser")
    public ResponseEntity getReservationForUser(@RequestParam Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty())
            return new ResponseEntity("invalid user", HttpStatus.BAD_REQUEST);
        return new ResponseEntity(reservationRepository.findAllReservationByUser(user.get().getId()), HttpStatus.OK);
    }

    @PostMapping("/doReservation")
    public ResponseEntity addNewShelter(@RequestBody ReservationRequest reservationRequest) {

        //check user
        if (reservationRequest.getUser()==null)
            return new ResponseEntity("user field is null", HttpStatus.BAD_REQUEST);
        Optional<User> user = userRepository.findById(reservationRequest.getUser());
        if (user.isEmpty())
            return new ResponseEntity("user not foud for id: " + reservationRequest.getUser(), HttpStatus.BAD_REQUEST);
        //check that's user select bivouac or shelter not both
//        if (reservationRequest.getBivouac()!=null && reservationRequest.getStructure()!=null)
//            return new ResponseEntity("ERROR: you must select a shelter or a bivouac ", HttpStatus.BAD_REQUEST);
        //check if there are at least 1 guest
        if (reservationRequest.getGuests() < 1)
            return new ResponseEntity("ERROR: the number of guest must be > 0", HttpStatus.BAD_REQUEST);
        //check that's the selected date isn't null
        if (reservationRequest.getFirstDay() == null)
            return new ResponseEntity("invalid data: first day is null", HttpStatus.BAD_REQUEST);
        //check that's first day is before last day
        if (!reservationRequest.getFirstDay().before(reservationRequest.getLastDay()))
            return new ResponseEntity("invalid data: first day is after last day", HttpStatus.BAD_REQUEST);
        //check that's the date isn't before the current date
        if (reservationRequest.getFirstDay().before(new Date()))
            return new ResponseEntity("invalid data: first day is before current data", HttpStatus.BAD_REQUEST);
        //check that's the selected date isn't null
        if (reservationRequest.getLastDay() == null)
            return new ResponseEntity("invalid data: last day is null", HttpStatus.BAD_REQUEST);
        //check that's the date isn't before the current date
        if (reservationRequest.getLastDay().before(new Date()))
            return new ResponseEntity("invalid data: last day is before current data", HttpStatus.BAD_REQUEST);
/*reservation for shelter*/
        if (reservationRequest.getStructure()!=null){
            Optional<Structure> structure = structureRepository.findById(reservationRequest.getStructure());
            if(structure.isEmpty())
                return new ResponseEntity("ERROR: structure not found for id: " + reservationRequest.getStructure(), HttpStatus.BAD_REQUEST);
            //check that's the date isn't before the structure's open date
            if (reservationRequest.getFirstDay().before(structure.get().getOpen()))
                return new ResponseEntity("the structure is closed for the selected data", HttpStatus.BAD_REQUEST);
            //check that's the date isn't after the structure close date
            if (reservationRequest.getLastDay().after(structure.get().getClose()))
                return new ResponseEntity("the structure is closed for the selected data", HttpStatus.BAD_REQUEST);
/* DO RESERVATION  */
            //get all reservation of the selected structure for date
            List<ReservationResponse> reservationForShelter = reservationRepository.findReservationByDateAndStructure(reservationRequest.getFirstDay(), reservationRequest.getLastDay(), reservationRequest.getStructure());
            if (!reservationForShelter.isEmpty()){
                //count bad already reserved and check if there is enough space
                int bedCount = 0;
                for (ReservationResponse r  : reservationForShelter)
                    bedCount += r.getGuest();
                if (bedCount+reservationRequest.getGuests() > structure.get().getMaxNumBed())
                    return new ResponseEntity("there's not enought space for reservation", HttpStatus.BAD_REQUEST);
            }
            reservationRepository.save(new Reservation(user.get(), structure.get(), reservationRequest.getGuests(), reservationRequest.getFirstDay(), reservationRequest.getLastDay()));
            return new ResponseEntity("reservation successfully evaluated", HttpStatus.OK);
        }
///* reservation for bivouac  */
//        if (reservationRequest.getBivouac()!=null){
//            Optional<Bivouac> bivouac = bivouacRepository.findById(reservationRequest.getBivouac());
//            if (bivouac.isEmpty())
//                return new ResponseEntity("ERROR: no bivouac found for id: " + reservationRequest.getBivouac(), HttpStatus.BAD_REQUEST);
//
//            if (!bivouac.get().isOpen())
//                return new ResponseEntity("sorry the bivouac is closed", HttpStatus.BAD_REQUEST);
//            //take all the active request of a bivouac for a specific date
//            List<ReservationResponse> reservationForBivouac = reservationRepository.findReservationByDateAndBivouac(reservationRequest.getFirstDay(), reservationRequest.getLastDay(), bivouac.get().getId());
//            if (!reservationForBivouac.isEmpty()) {
//                //in case of another request calculate if there is enough space for the current request
//                int totalGuests = 0;
//                //iterate all id to get the overnight request
//                for (ReservationResponse r : reservationForBivouac)
//                    totalGuests = totalGuests + r.getGuest();
//                if (totalGuests + reservationRequest.getGuests() > bivouac.get().getBed())  //magari si può aggiunegere un pop-up per far confermare la richiesta
//                    return new ResponseEntity("we're sorry there's not enough space to satisfy your accomodation", HttpStatus.BAD_REQUEST);
//                if (totalGuests>0)
//                    reservationRepository.save(new Reservation(user.get(), bivouac.get(), reservationRequest.getGuests(), reservationRequest.getFirstDay(), reservationRequest.getLastDay()));
//            }
//            reservationRepository.save(new Reservation(user.get(), bivouac.get(), reservationRequest.getGuests(), reservationRequest.getFirstDay(), reservationRequest.getLastDay()));
//            return new ResponseEntity("your overnight is signed correctly  ", HttpStatus.OK);
//        }
        return new ResponseEntity("ERROR: it suppose you're not be here", HttpStatus.BAD_GATEWAY);
    }

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
