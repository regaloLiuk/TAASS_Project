package com.example.mountbook_backend.repository;

import com.example.mountbook_backend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Long, Reservation> {
}
