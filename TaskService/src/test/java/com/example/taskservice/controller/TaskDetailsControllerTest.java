package com.example.multimodule.service.controller;

import com.example.multimodule.service.business.Service.TaskDetailsService;
import com.example.multimodule.service.controller.TaskDetailsController;
import com.example.multimodule.service.model.TaskDetails;
import com.example.multimodule.service.model.TaskDetailsRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskDetailsController.class)
public class TaskDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TaskDetailsService taskDetailsService;

    @Test
    void testCreateTaskDetails() throws Exception {
        TaskDetailsRequest taskDetailsRequest = new TaskDetailsRequest();
        taskDetailsRequest.setDescription("Sample Task Details");
        TaskDetails createdTaskDetails = new TaskDetails();
        createdTaskDetails.setDescription(taskDetailsRequest.getDescription());
        when(taskDetailsService.createTaskDetails(Mockito.anyLong(), Mockito.any(TaskDetails.class))).thenReturn(createdTaskDetails);
        mockMvc.perform(MockMvcRequestBuilders.post("/task-details/{taskId}/create", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskDetailsRequest)))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.description")
                        .value(taskDetailsRequest.getDescription()));
    }

    @Test
    void testGetTaskDetails_ExistingTaskId() throws Exception {
        Long taskId = 1L;
        TaskDetails taskDetails = new TaskDetails();

        when(taskDetailsService.findTaskDetailsByTaskDetailsId(taskId)).thenReturn(Optional.of(taskDetails));

        mockMvc.perform(get("/task-details/{taskId}/get", taskId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(taskDetails));

        verify(taskDetailsService, times(1)).findTaskDetailsByTaskDetailsId(taskId);
    }
    @Test
    void testGetTaskDetails_NonExistingTaskId() throws Exception {
        Long taskId = 2L;

        when(taskDetailsService.findTaskDetailsByTaskDetailsId(taskId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/task-details/{taskId}/get", taskId))
                .andExpect(status().isNotFound());

        verify(taskDetailsService, times(1)).findTaskDetailsByTaskDetailsId(taskId);
    }

}
