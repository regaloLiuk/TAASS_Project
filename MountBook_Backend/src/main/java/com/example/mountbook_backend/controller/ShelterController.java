package com.example.mountbook_backend.controller;

import java.sql.Date;
import java.util.*;

import com.example.mountbook_backend.entity.Reservation;
import com.example.mountbook_backend.entity.Shelter;
import com.example.mountbook_backend.payload.request.ShelterFilterRequest;
import com.example.mountbook_backend.repository.ReservationRepository;
import com.example.mountbook_backend.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shelter")
public class ShelterController {

    @Autowired
    ShelterRepository shelterRepository;

    @Autowired
    ReservationRepository reservationRepository;


    @GetMapping("/findAll")
    public ResponseEntity getAllFreeShelter(){
        Set<Shelter> result = new HashSet<>();
        List<Shelter> sheltersNotReserved = shelterRepository.findAllSheltersReserved();
        for (Shelter s : sheltersNotReserved)
            result.add(s);
        List<Shelter> sheltersReserved = shelterRepository.findAllSheltersNotReserved();
        for (Shelter s : sheltersReserved){
            int countBed = 0;
            List<Reservation> reservations = reservationRepository.findReservationByShelter(s.getId());
            for (Reservation r : reservations){
                countBed+=r.getGuests();
            }
            if (countBed <= s.getMaxNumBed())
                result.add(s);
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

//    @GetMapping("/findByDate")
//    public ResponseEntity getFreeShelter(@RequestParam Date dateStart, @RequestParam Date dateEnd){
//
//        List<Shelter> sheltersNotReserved = shelterRepository.findSheltersNotReservedByDate(dateStart, dateEnd);
//        Set<Shelter> result = new HashSet<>();
//        for (Shelter s : sheltersNotReserved)
//            result.add(s);
//
//        List<Shelter> sheltersReserved = shelterRepository.findSheltersReservedByDate(dateStart, dateEnd);
//        //itrerate all reserved shelter and check number of guest
//        for (Shelter s : sheltersReserved){
//            int countBed = 0;
//            List<Reservation> reservations = reservationRepository.findReservationByDateAndShelter(dateStart, dateEnd,s.getId());
//            for (Reservation r : reservations){
//                countBed+=r.getGuests();
//            }
//            if (countBed <= s.getMaxNumBed())
//                result.add(s);
//        }
//
//        if(result.isEmpty())
//            return new ResponseEntity<>("no shelter found for this date", HttpStatus.BAD_REQUEST);
//
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

//    @GetMapping("/findByService")
//    public ResponseEntity getSheleterFromService(@RequestParam Boolean wifi, @RequestParam Boolean car,
//                                                 @RequestParam Boolean equipment){
//        List<Shelter> shelters = shelterRepository.findShelterByService(wifi,equipment,car);
//        if (shelters.isEmpty())
//            return new ResponseEntity("no shelter found with this services", HttpStatus.BAD_REQUEST);
//        return new ResponseEntity(shelters, HttpStatus.OK);
//    }

//    @GetMapping("/findByPrice")
//    public ResponseEntity getShelterFromPrice(@RequestParam float minPrice, @RequestParam float maxPrice){
//        if (maxPrice < 0)
//            maxPrice = 999999;
//        List<Shelter> shelters = shelterRepository.findShelterByPrice(minPrice,maxPrice);
//        if (shelters.isEmpty())
//            return new ResponseEntity("no shelter found with this price", HttpStatus.BAD_REQUEST);
//        return new ResponseEntity(shelters, HttpStatus.OK);
//    }

    //unique method for get shelter by filter
    @GetMapping("/findFreeShelterByUserFilter")
    public ResponseEntity findByUserFilter(@RequestBody ShelterFilterRequest request){
        Set<Shelter> freeForDate = new HashSet<>();

        //check for date
        if (request.getDateStart() != null && request.getDateEnd()!=null){
            List<Shelter> sheltersNotReserved = shelterRepository.findSheltersNotReservedByDate(request.getDateStart(), request.getDateEnd());
            for (Shelter s : sheltersNotReserved)
                freeForDate.add(s);

            List<Shelter> sheltersReserved = shelterRepository.findSheltersReservedByDate(request.getDateStart(), request.getDateEnd());
            //itrerate all reserved shelter and check number of guest
            for (Shelter s : sheltersReserved){
                int countBed = 0;
                List<Reservation> reservations = reservationRepository.findReservationByDateAndShelter(request.getDateStart(), request.getDateEnd(),s.getId());
                for (Reservation r : reservations){
                    countBed+=r.getGuests();
                }
                if(request.getGuest() > 0){ //
                    if (countBed + request.getGuest() <= s.getMaxNumBed())
                        freeForDate.add(s);
                }else { //at least 1 free bad
                    if (countBed <= s.getMaxNumBed())
                        freeForDate.add(s);
                }
            }
        }
        Set<Shelter> fitInPrice = new HashSet<>();
        //check for price
        if (request.getMinPrice() >= 0 && request.getMaxPrice()>=0) {
            List<Shelter> s1 = shelterRepository.findShelterByPrice(request.getMinPrice(), request.getMaxPrice());
            for (Shelter s : s1)
                fitInPrice.add(s);
        }
        freeForDate.retainAll(fitInPrice);

        //check for service
        if (request.getWifi()||request.getCar()||request.getEquipment()) {

            Set<Shelter> haveServices = new HashSet<>();
            if (request.getWifi()) {
                for (Shelter s : shelterRepository.findShelterWithWifi())
                    haveServices.add(s);
            }

            if (request.getCar()) {
                Set<Shelter> haveCar = new HashSet<>();
                if (!haveServices.isEmpty()) {
                    for (Shelter s : shelterRepository.findShelterWithCar())
                        haveCar.add(s);
                    haveServices.retainAll(haveCar);
                }else{
                    for (Shelter s : shelterRepository.findShelterWithCar())
                        haveServices.add(s);
                }
            }

            if (request.getEquipment()) {
                Set<Shelter> haveEquipment = new HashSet<>();
                if (!haveServices.isEmpty()) {
                    for (Shelter s : shelterRepository.findShelterWithEquipment())
                        haveEquipment.add(s);
                    haveServices.retainAll(haveEquipment);
                }else{
                    for (Shelter s : shelterRepository.findShelterWithEquipment())
                        haveServices.add(s);
                }
            }
            freeForDate.retainAll(haveServices);
        }

        return new ResponseEntity(freeForDate, HttpStatus.OK);
    }
}
