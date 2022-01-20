package com.example.mountbook_backend.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.example.mountbook_backend.entity.Bivouac;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface BivouacRepository extends JpaRepository<Bivouac,Long>{
    
    public List<Bivouac> findAll();
    
    public Optional<Bivouac> findByName(String name);

}
