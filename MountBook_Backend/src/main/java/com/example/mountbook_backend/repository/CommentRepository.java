package com.example.mountbook_backend.repository;

import com.example.mountbook_backend.entity.Comment;
import com.example.mountbook_backend.entity.Outing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findById(Long comment);

    List<Comment> findAllByShelter(Long shelter_id);

    List<Comment> findAllByUser(Long user_id);


}
