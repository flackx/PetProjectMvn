package com.example.multimodule.service.mapper;

import com.example.multimodule.service.model.Task;
import com.example.multimodule.service.model.dto.TaskDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "status", expression = "java(task.getStatus().name())")
    TaskDTO taskToTaskDTO(Task task);
}
