package com.example.mountbook_backend.controller;

import com.example.mountbook_backend.entity.Bivouac;
import com.example.mountbook_backend.entity.OvernightStays;
import com.example.mountbook_backend.entity.User;
import com.example.mountbook_backend.payload.request.OvernightStayRequest;
import com.example.mountbook_backend.repository.BivouacRepository;
import com.example.mountbook_backend.repository.OvernightStaysRepository;
import com.example.mountbook_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/OvernightStays")
public class OvernightStaysController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BivouacRepository bivouacRepository;
    @Autowired
    OvernightStaysRepository overnightStaysRepository;

    //sign the presence of user in a Bivouac for a specific date
    @PostMapping("/overnightStayRequest")
    public ResponseEntity overnightStayRequest(@RequestBody OvernightStayRequest request) {
        Optional<User> user = userRepository.findById(request.getUser());
        if (user.isEmpty())
            return new ResponseEntity("no user found", HttpStatus.BAD_REQUEST);
        Optional<Bivouac> bivouac = bivouacRepository.findById(request.getBivouac());
        if (bivouac.isEmpty())
            return new ResponseEntity("no bivouac found", HttpStatus.BAD_REQUEST);
        if (!bivouac.get().isOpen())
            return new ResponseEntity("sorry the bivouac is closed", HttpStatus.BAD_REQUEST);
        //take all the active request of a bivouac for a specific date
        List<Long> overnightRequest = overnightStaysRepository.existsRequestFromDateAndBivouac(request.getFirstDay(), request.getLastDay(), bivouac.get().getId());
        if (!overnightRequest.isEmpty()) {
            //in case of another request calculate if there is enough space for the current request
            int totalGuests = 0;
            //iterate all id to get the overnight request
            for (Long id : overnightRequest) {
                Optional<OvernightStays> overnightStay = overnightStaysRepository.findById(id);
                if (overnightStay.isEmpty())
                    return new ResponseEntity("ERROR: the id not correspond to any request", HttpStatus.BAD_REQUEST);
                totalGuests = totalGuests + overnightStay.get().getGuest();
            }
            if (totalGuests + request.getGuests() > bivouac.get().getBed())
                return new ResponseEntity("we're sorry there's not enough space to satisfy yoou accomodation", HttpStatus.BAD_REQUEST);

            //magari si può aggiunegere un pop-up per far confermare la richiesta
            overnightStaysRepository.save(new OvernightStays(bivouac.get(), user.get(), request.getGuests(), request.getFirstDay(), request.getLastDay()));
            return new ResponseEntity("your overnight is signed correctly, but there are other people in the bivouac", HttpStatus.OK);
        }

        overnightStaysRepository.save(new OvernightStays(bivouac.get(), user.get(), request.getGuests(), request.getFirstDay(), request.getLastDay()));
        return new ResponseEntity("your overnight is signed correctly", HttpStatus.OK);
    }

    @PostMapping("/overnightStayDelete")
    public ResponseEntity removeOvernightStaySign(@RequestParam Long overnightId, Long userId) {
        overnightStaysRepository.deleteSign(overnightId, userId);
        return new ResponseEntity("your sign is correctly removed", HttpStatus.OK);
    }
}