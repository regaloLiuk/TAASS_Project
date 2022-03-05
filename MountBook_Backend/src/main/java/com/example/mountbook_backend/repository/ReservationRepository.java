package com.example.mountbook_backend.repository;

import com.example.mountbook_backend.entity.Reservation;
import com.example.mountbook_backend.entity.User;
import com.example.mountbook_backend.payload.responce.ReservationResponse;
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

    @Query(value = "SELECT new com.example.mountbook_backend.payload.responce.ReservationResponse(r.id,r.shelter.id, r.user.id, r.guests, r.firstDay, r.lastDay) FROM Reservation r WHERE r.user.id=:user_id")
    List<ReservationResponse> findAllByUser(@Param("user_id") Long userId);

    @Query(value = "SELECT new com.example.mountbook_backend.payload.responce.ReservationResponse(r.id,r.shelter.id, r.user.id, r.guests, r.firstDay, r.lastDay) FROM Reservation r WHERE (r.firstDay<=:first_day OR r.lastDay>=:last_day)")
    List<ReservationResponse> findReservationByDate(@Param("first_day") Date firstDay, @Param("last_day") Date lastDay);

    @Query(value = "SELECT new com.example.mountbook_backend.payload.responce.ReservationResponse(r.id,r.shelter.id, r.user.id, r.guests, r.firstDay, r.lastDay) FROM Reservation r  WHERE (r.firstDay<=:first_day OR r.lastDay>=:last_day) AND r.shelter.id=:shelter")
    List<ReservationResponse> findReservationByDateAndShelter(@Param("first_day") Date firstDay, @Param("last_day") Date lastDay, @Param("shelter") Long shelter);

    @Query(value = "SELECT new com.example.mountbook_backend.payload.responce.ReservationResponse(r.id,r.shelter.id, r.user.id, r.guests, r.firstDay, r.lastDay) FROM Reservation r  WHERE  r.shelter.id=:shelter")
    List<ReservationResponse> findReservationByShelter(@Param("shelter") Long shelter);




}








