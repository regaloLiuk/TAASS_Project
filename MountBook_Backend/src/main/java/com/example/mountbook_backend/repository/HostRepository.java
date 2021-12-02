package com.example.mountbook_backend.repository;

import com.example.mountbook_backend.entity.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface HostRepository extends JpaRepository<Host, Long> {

    Optional<Host> findByEmail(String email);

    @Modifying
    @Query("UPDATE Host h SET h.password=:newPassword WHERE h.email=:email")
    void updatePassword(String email, String newPassword);
}
