package com.example.mountbook_backend.repository;

import com.example.mountbook_backend.entity.Reservation;
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

    @Query(value = "SELECT new com.example.mountbook_backend.payload.responce.ReservationResponse(r.id, r.structure.id, r.user.id, r.guests, r.firstDay, r.lastDay, r.structure.name) FROM Reservation r WHERE r.user.id=:user_id")
    List<ReservationResponse> findAllReservationByUser(@Param("user_id") Long userId);

    @Query(value = "SELECT new com.example.mountbook_backend.payload.responce.ReservationResponse(r.id, r.structure.id, r.user.id, r.guests, r.firstDay, r.lastDay, r.structure.name) FROM Reservation r WHERE (r.firstDay<=:first_day OR r.lastDay>=:last_day)")
    List<ReservationResponse> findReservationByDate(@Param("first_day") Date firstDay, @Param("last_day") Date lastDay);

    @Query(value = "SELECT new com.example.mountbook_backend.payload.responce.ReservationResponse(r.id,r.structure.id, r.user.id, r.guests, r.firstDay, r.lastDay, r.structure.name) FROM Reservation r  WHERE (r.firstDay<=:first_day OR r.lastDay>=:last_day) AND r.structure.id=:structure")
    List<ReservationResponse> findReservationByDateAndStructure(@Param("first_day") Date firstDay, @Param("last_day") Date lastDay, @Param("structure") Long structure);

    @Query(value = "SELECT new com.example.mountbook_backend.payload.responce.ReservationResponse(r.id,r.structure.id, r.user.id, r.guests, r.firstDay, r.lastDay, r.structure.name) FROM Reservation r  WHERE  r.structure.id=:structure")
    List<ReservationResponse> findReservationsOfStructure(@Param("structure") Long structure);

}








