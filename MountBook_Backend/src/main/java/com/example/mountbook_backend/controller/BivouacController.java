package com.example.mountbook_backend.controller;

import com.example.mountbook_backend.entity.Bivouac;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.mountbook_backend.repository.BivouacRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Optional;


@RestController
@RequestMapping("/api/bivouac")
public class BivouacController {
    
    @Autowired
    BivouacRepository bivouacRepository;

    @GetMapping("/getAllBivouac")
    public ResponseEntity getAllBivouac(){
        return ResponseEntity.ok(bivouacRepository.findAll());
    }

    @PostMapping("/updateOpenStatus")
    public ResponseEntity updateOpenStatus(Bivouac bivouac){

        bivouacRepository.updateOpenState(!bivouac.isOpen());
        if (bivouac.isOpen())
            return new ResponseEntity("now the bivouac is closed", HttpStatus.OK);
        return new ResponseEntity("now the bivouac is open", HttpStatus.OK);

    }

    @GetMapping("/getBivouacByName")
    public ResponseEntity getBivouacByName(@RequestParam String name){

        Optional<Bivouac> bivouac = bivouacRepository.findByName(name);
        if (bivouac.isEmpty())
            return new ResponseEntity("no bivouac found", HttpStatus.NOT_FOUND);
        return new ResponseEntity(bivouac.get(), HttpStatus.OK);
    }

}
