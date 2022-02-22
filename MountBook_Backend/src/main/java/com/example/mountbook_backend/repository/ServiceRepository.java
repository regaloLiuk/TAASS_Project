package com.example.mountbook_backend.repository;

import com.example.mountbook_backend.entity.Service;
import com.example.mountbook_backend.entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    @Query(value="SELECT (s) FROM Service s WHERE s.shelter=:shelter")
    List<Service> findByShelter(@Param("shelter") Shelter shelter);
}
