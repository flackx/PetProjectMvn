package com.example.multimodule.service.business.Repository;

import com.example.multimodule.service.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

   // Task findByCommentId(Long commentId);

}