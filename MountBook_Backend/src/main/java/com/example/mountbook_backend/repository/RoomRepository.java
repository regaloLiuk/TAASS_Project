package com.example.mountbook_backend.repository;

import com.example.mountbook_backend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long>{

    @Query(value =  "SELECT * " +
                    "FROM Room r " +
                    "WHERE r.shelter.id=:shelter_id AND r.id NOT IN (" +
                    "SELECT * " +
                    "FROM Room r1 JOIN Reservation ON (r1.reservation.id = Reservation.id))", nativeQuery = true)
    List<Room> getRoomNotReserved(@Param("shelter_id") Long shelterId);

    @Query(value = "SELECT rs.room_id FROM reserved_rooms rs WHERE rs.reservation_id=:reservation_id", nativeQuery = true)
    List<Long> getRoomByReservation(@Param("reservation_id") Long reservationId);

}
