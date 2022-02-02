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

    //verify that's the rooms are not reserved for the date selected
//    @Query(value = "SELECT r.id FROM reservation r JOIN reserved_rooms rs ON (r.id = rs.reservation_id) WHERE (r.first_day<=:first_day OR r.last_day>=:last_day) AND rs.room_id IN :rooms", nativeQuery = true)
//    List<Long> existsReservationByFirstDayAndLastDayAndReservedRooms(@Param("first_day") Date firstDay, @Param("last_day") Date lastDay, @Param("rooms") List<Long> rooms);

    @Query(value = "SELECT r FROM Reservation r  WHERE (r.firstDay<=:first_day OR r.lastDay>=:last_day) AND r.shelter.id IN :shelter")
    List<Reservation> findReservationByDateAndShelter(@Param("first_day") Date firstDay, @Param("last_day") Date lastDay, @Param("shelter") Long shelter);



}








