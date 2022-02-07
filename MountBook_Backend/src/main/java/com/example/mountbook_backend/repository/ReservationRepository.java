package com.example.mountbook_backend.repository;

import com.example.mountbook_backend.entity.Reservation;
import com.example.mountbook_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findById(Long reservation);

    List<Reservation> findAllByUser(User user);

    @Query(value = "SELECT r FROM Reservation r WHERE (r.firstDay<=:first_day OR r.lastDay>=:last_day)")
    List<Reservation> findReservationByDate(@Param("first_day") Date firstDay, @Param("last_day") Date lastDay);

    @Query(value = "SELECT r FROM Reservation r  WHERE (r.firstDay<=:first_day OR r.lastDay>=:last_day) AND r.shelter.id=:shelter")
    List<Reservation> findReservationByDateAndShelter(@Param("first_day") Date firstDay, @Param("last_day") Date lastDay, @Param("shelter") Long shelter);

    @Query(value = "SELECT r FROM Reservation r  WHERE  r.shelter.id=:shelter")
    List<Reservation> findReservationByShelter(@Param("shelter") Long shelter);




}








