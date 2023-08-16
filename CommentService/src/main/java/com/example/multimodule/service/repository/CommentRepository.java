package com.example.multimodule.service.repository;

import com.example.multimodule.service.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // You can define additional query methods here if needed
}
