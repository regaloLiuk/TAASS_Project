package com.example.mountbook_backend.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.mountbook_backend.entity.Shelter;
import com.example.mountbook_backend.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shelter")
public class ShelterController {

    @Autowired
    ShelterRepository shelterRepository;

    @GetMapping("/findByDate")
    public ResponseEntity getFreeShelter(@RequestParam Date dateStart, @RequestParam Date dateEnd){
        List<Shelter> shelters = new ArrayList<Shelter>();
        List<Long> ids = shelterRepository.findSheltersIdByDate(dateStart, dateEnd);
        if(ids.isEmpty())
            return new ResponseEntity<>("no shelter found for this date", HttpStatus.BAD_REQUEST);
        for(Long id : ids){
            Optional<Shelter> shelter = shelterRepository.findById(id);
            if(shelter.isEmpty())
                return new ResponseEntity<>("error this id is invalid", HttpStatus.BAD_GATEWAY);
            shelters.add(shelter.get());
        }
        return new ResponseEntity<>(shelters, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity getFreeShelter(){
        return new ResponseEntity(shelterRepository.findAll(), HttpStatus.OK);
    }


        @GetMapping("/findByService")
    public ResponseEntity getSheleterFromService(@RequestParam Boolean wifi, @RequestParam Boolean car,
                                                 @RequestParam Boolean equipment){
        List<Shelter> shelters = shelterRepository.findShelterIdByServece(wifi,equipment,car);
        if (shelters.isEmpty())
            return new ResponseEntity("no shelter found with this services", HttpStatus.BAD_REQUEST);
        return new ResponseEntity(shelters, HttpStatus.OK);
    }

    @GetMapping("/findByPrice")
    public ResponseEntity getShelterFromPrice(@RequestParam float minPrice, @RequestParam float maxPrice){
        List<Shelter> shelters = shelterRepository.findShelterByPrice(minPrice,maxPrice);
        if (shelters.isEmpty())
            return new ResponseEntity("no shelter found with this price", HttpStatus.BAD_REQUEST);
        return new ResponseEntity(shelters, HttpStatus.OK);
    }

}
