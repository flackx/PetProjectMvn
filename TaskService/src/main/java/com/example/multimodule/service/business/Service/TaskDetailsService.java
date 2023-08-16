package com.example.multimodule.service.business.Service;


import com.example.multimodule.service.model.TaskDetails;

import java.util.Optional;


public interface TaskDetailsService {
    TaskDetails createTaskDetails(Long taskId, TaskDetails taskDetails);

    Optional <TaskDetails> findTaskDetailsByTaskDetailsId(Long taskId);

    void deleteTaskDetailsById(Long taskDetailsId);

    Optional<TaskDetails> updateTaskDetails(Long taskDetailsId, TaskDetails updatedTaskDetails);
}
