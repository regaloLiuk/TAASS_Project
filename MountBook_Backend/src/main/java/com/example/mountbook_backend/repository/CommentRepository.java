package com.example.mountbook_backend.repository;

import com.example.mountbook_backend.entity.Comment;
import com.example.mountbook_backend.payload.responce.CommentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findById(Long comment);

    List<Comment> findAllByStructure(Long structure_id);

    @Query(value = "SELECT new com.example.mountbook_backend.payload.responce.CommentResponse(c.id,c.structure.id, c.user.id, c.service, c.clear, c.ospitality,c.food,c.location) FROM Comment c WHERE c.user.id=:user_id")
    List<CommentResponse> findAllByUser(@Param("user_id") Long userId);

    @Query(value = "SELECT new com.example.mountbook_backend.payload.responce.CommentResponse(c.id,c.structure.id, c.user.id, c.service, c.clear, c.ospitality,c.food,c.location) FROM Comment c WHERE c.user.id=:user_id AND c.structure.id=:structure_id")
    Optional<CommentResponse> findAllByUserAndStructure(@Param("user_id") Long userId, @Param("structure_id") Long structure_id);


}
