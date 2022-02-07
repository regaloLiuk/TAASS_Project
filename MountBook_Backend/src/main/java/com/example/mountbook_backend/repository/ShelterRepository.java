package com.example.mountbook_backend.repository;

import com.example.mountbook_backend.entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter,Long> {

    Optional<Shelter> findById(Long shelterId);

    List<Shelter> findAll();

    @Query(value = "SELECT DISTINCT (s) FROM Shelter s WHERE s NOT IN (" +
            " SELECT s1 FROM Shelter s1 JOIN Reservation res ON (s1.id = res.id))")
    List<Shelter> findAllSheltersNotReserved();

    @Query(value = "SELECT DISTINCT (s) FROM Shelter s WHERE s.id IN (" +
            " SELECT s1 FROM Shelter s1 JOIN Reservation res ON (s1.id = res.id))")
    List<Shelter> findAllSheltersReserved();

    @Query(value = "SELECT DISTINCT (s) FROM Shelter s WHERE s.id NOT IN (" +
                   " SELECT s1 FROM Shelter s1 JOIN Reservation res ON (s1.id = res.id) WHERE res.firstDay=:date_start AND res.lastDay=:date_end)")
    List<Shelter> findSheltersNotReservedByDate(@Param("date_start") Date dateStart, @Param("date_end") Date dateEnd);

    @Query(value = "SELECT DISTINCT (s) FROM Shelter s WHERE s.id IN (" +
            " SELECT s1 FROM Shelter s1 JOIN Reservation res ON (s1.id = res.id) WHERE res.firstDay=:date_start AND res.lastDay=:date_end)")
    List<Shelter> findSheltersReservedByDate(@Param("date_start") Date dateStart, @Param("date_end") Date dateEnd);

    @Query(value = "SELECT s.shelter FROM Service s WHERE s.wifi=:wifi OR s.equipment=:equipment OR s.car=:car")
    List<Shelter> findShelterByService(@Param("wifi") boolean wifi, @Param("equipment") boolean equipment, @Param("car") boolean car);

    @Query(value = "SELECT s FROM Shelter s WHERE s.price>=:min_price AND s.price<=:max_price")
    List<Shelter> findShelterByPrice(@Param("min_price") float minPrice, @Param("max_price") float maxPrice);

}
