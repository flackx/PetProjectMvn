package com.example.multimodule.service.serializer;

import com.example.multimodule.service.model.Task;
import com.example.multimodule.service.model.dto.TaskDTO;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
public class TaskSerializer extends JsonSerializer<Task> {

    @Override
    public void serialize(Task task, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDueDate(task.getDueDate());
        taskDTO.setStatus(String.valueOf(task.getStatus()));

        gen.writeObject(taskDTO);
    }
}