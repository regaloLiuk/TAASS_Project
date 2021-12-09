package com.example.mountbook_backend.repository;

import com.example.mountbook_backend.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
