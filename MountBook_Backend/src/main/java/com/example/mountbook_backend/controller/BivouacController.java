package com.example.mountbook_backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.mountbook_backend.repository.BivouacRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/bivouac")
public class BivouacController {
    
    @Autowired
    BivouacRepository bivouacRepository;

    @GetMapping("/getAllBivouac")
    public ResponseEntity getAllBivouac(){
        return ResponseEntity.ok(bivouacRepository.findAll());
    }

}
