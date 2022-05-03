package com.example.mountbook_backend.controller;

import com.example.mountbook_backend.entity.Structure;
import com.example.mountbook_backend.repository.ServiceRepository;
import com.example.mountbook_backend.repository.StructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "", allowedHeaders = "")
@RestController
@RequestMapping("/api/v1/service")
public class ServiceController {

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    StructureRepository structureRepository;

    @GetMapping("/getServicesForShelter")
    public ResponseEntity getServicesForShelter(@RequestParam Long shelterId){
        Optional<Structure> shelter= structureRepository.findById(shelterId);
        if(shelter.isEmpty()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity(serviceRepository.findByShelter(shelter.get()), HttpStatus.OK);
        }

    }
}
