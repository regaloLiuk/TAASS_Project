package com.example.mountbook_backend.repository;

import com.example.mountbook_backend.entity.Outing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutingRepository extends JpaRepository<Outing, Long> {
}
