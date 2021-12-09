package com.example.mountbook_backend.repository;

import com.example.mountbook_backend.entity.Outing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutingRepository extends JpaRepository<Long, Outing> {
}
