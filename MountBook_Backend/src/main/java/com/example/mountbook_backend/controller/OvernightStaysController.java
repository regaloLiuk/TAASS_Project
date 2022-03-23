package com.example.mountbook_backend.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/OvernightStays")
public class OvernightStaysController {
/*
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
        List<OvernightStays> overnightRequest = overnightStaysRepository.findRequestFromDateAndBivouac(request.getFirstDay(), request.getLastDay(), bivouac.get().getId());
        if (!overnightRequest.isEmpty()) {
            if(request.getGuests()<0)
                return new ResponseEntity("for a correct request there must be at least 1 guest", HttpStatus.BAD_REQUEST);
            //in case of another request calculate if there is enough space for the current request
            int totalGuests = 0;

            //iterate all id to get the overnight request
            for (OvernightStays os : overnightRequest)
                totalGuests = totalGuests + os.getGuest();

            if (totalGuests + request.getGuests() > bivouac.get().getBed())
                return new ResponseEntity("we're sorry there's not enough space to satisfy your accomodation", HttpStatus.BAD_REQUEST);

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
    }*/
}