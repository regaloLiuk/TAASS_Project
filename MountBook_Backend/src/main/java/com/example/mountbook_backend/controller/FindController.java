package com.example.mountbook_backend.controller;

import com.example.mountbook_backend.entity.Bivouac;
import com.example.mountbook_backend.entity.OvernightStays;
import com.example.mountbook_backend.entity.Reservation;
import com.example.mountbook_backend.entity.Shelter;
import com.example.mountbook_backend.payload.request.UserFilterRequest;
import com.example.mountbook_backend.repository.BivouacRepository;
import com.example.mountbook_backend.repository.OvernightStaysRepository;
import com.example.mountbook_backend.repository.ReservationRepository;
import com.example.mountbook_backend.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/find")
public class FindController {

    @Autowired
    ShelterRepository shelterRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    BivouacRepository bivouacRepository;
    @Autowired
    OvernightStaysRepository overnightStaysRepository;

    @GetMapping("findStructure")
    public ResponseEntity findStructure(@RequestBody UserFilterRequest request) {

        //search for shelter
        Set<Object> shelterResult = new HashSet<>();
        if (request.getType() == 0 || request.getType() == 1) {
            //check for date
            if (request.getDateStart() != null && request.getDateEnd() != null) {
                //get all shelter not reserved for selected date
                List<Shelter> sheltersNotReserved = shelterRepository.findSheltersNotReservedByDate(request.getDateStart(), request.getDateEnd());
                for (Shelter s : sheltersNotReserved) {
                    if (s.getOpen().before(request.getDateStart()) && s.getClose().after(request.getDateEnd()))
                        shelterResult.add(s);
                }
                //get all remaining shelter with a reservation for selected date
                List<Shelter> sheltersReserved = shelterRepository.findSheltersReservedByDate(request.getDateStart(), request.getDateEnd());
                //iterate the list and check if the shelters can ospitate the guest
                for (Shelter s : sheltersReserved) {
                    if (s.getOpen().before(request.getDateStart()) && s.getClose().after(request.getDateEnd())) {
                        int countBed = 0;
                        //get all reservation for single shelter
                        List<Reservation> reservations = reservationRepository.findReservationByDateAndShelter(request.getDateStart(), request.getDateEnd(), s.getId());
                        //iterate the list and count the number of guest
                        for (Reservation r : reservations) {
                            countBed += r.getGuests();
                        }
                        if (request.getGuest() > 0) { //add number of guest
                            if (countBed + request.getGuest() <= s.getMaxNumBed())
                                shelterResult.add(s);
                        }
                    }
                }
            }

            //check for price
            Set<Object> fitInPrice = new HashSet<>();
            if (request.getMinPrice() >= 0 && request.getMaxPrice() >= 0) {
                List<Shelter> s1 = shelterRepository.findShelterByPrice(request.getMinPrice(), request.getMaxPrice());
                for (Shelter s : s1)
                    fitInPrice.add(s);
            }
            if (shelterResult.isEmpty())
                shelterResult.addAll(fitInPrice);
            else
                shelterResult.retainAll(fitInPrice);

            //check for service
            if (request.getWifi() || request.getCar() || request.getEquipment()) {
                Set<Object> haveServices = new HashSet<>();
                if (request.getWifi()) {
                    for (Shelter s : shelterRepository.findShelterWithWifi())
                        haveServices.add(s);
                }

                if (request.getCar()) {
                    Set<Object> haveCar = new HashSet<>();
                    for (Shelter s : shelterRepository.findShelterWithCar())
                        haveCar.add(s);
                    if (haveServices.isEmpty())
                        haveServices = haveCar;
                    else
                        haveServices.retainAll(haveCar);
                }

                if (request.getEquipment()) {
                    Set<Object> haveEquipment = new HashSet<>();
                    for (Shelter s : shelterRepository.findShelterWithEquipment())
                        haveEquipment.add(s);
                    if (haveServices.isEmpty())
                        haveServices = haveEquipment;
                    else
                        haveServices.retainAll(haveEquipment);
                }
                if (shelterResult.isEmpty())
                    shelterResult.addAll(haveServices);
                else
                    shelterResult.retainAll(haveServices);
            }
        }
        //search for bivouac
        Set<Object> bivouacResult = new HashSet<>();
        if (request.getType() == 0 || request.getType() == 2) {
            //check for date
            if (request.getDateStart() != null && request.getDateEnd() != null) {
                //get all bivouac without request for selected date
                List<Bivouac> bivouacsWithoutRequest = bivouacRepository.findWithoutRequestForDate(request.getDateStart(), request.getDateEnd());
                for (Bivouac b : bivouacsWithoutRequest) {
                    if (b.isOpen())
                        bivouacResult.add(b);
                }
                //get all remaining bivouac with a request for selected date
                List<Bivouac> bivouacsWithtRequest = bivouacRepository.findWithRequestForDate(request.getDateStart(), request.getDateEnd());
                //iterate the list and check if the bivouac can ospitate the guest
                for (Bivouac b : bivouacsWithoutRequest) {
                    if (b.isOpen()) {
                        int countBed = 0;
                        //get all request for single shelter
                        List<OvernightStays> overnightStaysRequest = overnightStaysRepository.findRequestFromDateAndBivouac(request.getDateStart(), request.getDateEnd(), b.getId());
                        //iterate the list and count the number of guest
                        for (OvernightStays os : overnightStaysRequest)
                            countBed += os.getGuest();
                        if (request.getGuest() > 0) { //add number of guest
                            if (countBed + request.getGuest() <= b.getBed())
                                shelterResult.add(b);
                        }
                        bivouacResult.add(b);
                    }
                }
            }
        }
        HashSet<Object> result = new HashSet<>();
        if (request.getType() == 0) {
            result.addAll(bivouacResult);
            result.addAll(shelterResult);
        }
        if (request.getType()==1)
            result.addAll(shelterResult);
        if (request.getType()==2)
            result.addAll(bivouacResult);

        return new ResponseEntity(result, HttpStatus.OK);
    }



}
