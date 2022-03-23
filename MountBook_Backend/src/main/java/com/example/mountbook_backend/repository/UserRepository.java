package com.example.mountbook_backend.repository;

import com.example.mountbook_backend.entity.User;
import com.example.mountbook_backend.payload.responce.UserMinimalResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query(value = "SELECT new com.example.mountbook_backend.payload.responce.UserMinimalResponse(u.id,u.username,u.email) FROM User u WHERE u.id=:user")
    Optional<UserMinimalResponse> findUserById(@Param("user") Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password=:newPassword WHERE u.email=:email")
    void updatePassword(String email, String newPassword);

}
