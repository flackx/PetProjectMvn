package com.example.multimodule.service.business.Service;
import com.example.multimodule.service.model.Task;
import com.example.multimodule.service.model.TaskRequest;
import com.example.multimodule.service.model.dto.TaskDTO;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Task createTask(TaskRequest taskRequest);

    Optional<Task> deleteTaskById(Long taskId);

    Optional<Task> getTaskById(Long Id);

    Optional <Task> updateTask(Long taskId, Task Task);

    List<Task> createTasks(List<TaskRequest> taskRequests);


    List<TaskDTO> getAllTaskDTOs();


    //void deleteCommentById(Long commentId);

}
