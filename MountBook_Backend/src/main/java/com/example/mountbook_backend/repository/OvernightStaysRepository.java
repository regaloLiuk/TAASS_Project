package com.example.mountbook_backend.repository;

import org.springframework.stereotype.Repository;

import antlr.collections.List;

import com.example.mountbook_backend.entity.OvernightStays;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface OvernightStaysRepository extends JpaRepository<OvernightStays,Long>{
    
    List<OvernightStays> get

}
