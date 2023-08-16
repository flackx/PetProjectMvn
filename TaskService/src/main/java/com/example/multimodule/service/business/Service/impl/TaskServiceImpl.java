package com.example.multimodule.service.business.Service.impl;

import com.example.multimodule.service.mapper.TaskMapper;
import com.example.multimodule.service.business.Repository.TaskDetailsRepository;
import com.example.multimodule.service.business.Repository.TaskRepository;
import com.example.multimodule.service.business.Service.TaskService;
import com.example.multimodule.service.model.Task;
import com.example.multimodule.service.model.TaskDetails;
import com.example.multimodule.service.model.TaskRequest;
import com.example.multimodule.service.model.dto.TaskDTO;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TaskServiceImpl implements TaskService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(TaskServiceImpl.class);
    private final TaskRepository taskRepository;
    private final TaskDetailsRepository taskDetailsRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TaskDetailsRepository taskDetailsRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskDetailsRepository = taskDetailsRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public Task createTask(TaskRequest taskRequest) {
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDueDate(taskRequest.getDueDate());
        task.setStatus(taskRequest.getStatus());
        return taskRepository.save(task);
    }
    @Override
    public Optional<Task> deleteTaskById(Long taskId) {
        log.info("Deleting task with id: {}", taskId);
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isPresent()) {
            log.info("Task with id: {} found", taskId);
            Task task = taskOptional.get();
            log.info("Deleting task details for task with id: {}", taskId);
            TaskDetails taskDetails = taskDetailsRepository.findByTask(task);
            if (taskDetails != null) {
                taskDetailsRepository.delete(taskDetails);
            }
            log.info("Deleting task with id: {}", taskId);
            taskRepository.deleteById(taskId);
        } else {
            log.info("Task with id: {} not found", taskId);
        }
        return taskOptional;
    }

    @Override
    public Optional<Task> updateTask(Long taskId, Task updatedTask) {
        return Optional.of(taskRepository.save(updatedTask));
    }

    @Override
    public Optional<Task>getTaskById(Long Id){
        return taskRepository.findById(Id);
    }


        public List<Task> createTasks(List<TaskRequest> taskRequests) {
            return taskRequests.stream()
                    .map(this::createTask)
                    .collect(Collectors.toList());
        }


    @Override
    @Transactional
    @Cacheable("tasks")

    public List<TaskDTO> getAllTaskDTOs() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(this::convertToTaskDTO)
                .collect(Collectors.toList());
    }

    private TaskDTO convertToTaskDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDueDate(task.getDueDate());
        taskDTO.setStatus(String.valueOf(task.getStatus()));
        return taskDTO;
    }


}
