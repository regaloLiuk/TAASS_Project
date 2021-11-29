package com.example.mountbook_backend.repository;

import com.example.mountbook_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User>findByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.password=:newPassword WHERE u.email=:email")
    void updatePassword(String email, String newPassword);

}
