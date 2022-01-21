package com.example.mountbook_backend.repository;

import com.example.mountbook_backend.entity.Bivouac;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;


@Repository
public interface BivouacRepository extends JpaRepository<Bivouac,Long>{

    public Optional<Bivouac> findByName(String name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Bivouac b SET b.isOpen=:state")
    public void updateOpenState(@Param("state") boolean state);


}
