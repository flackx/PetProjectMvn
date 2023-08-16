package com.example.multimodule.service.controller;

import com.example.multimodule.service.business.Service.TaskService;
import com.example.multimodule.service.controller.TasksController;
import com.example.multimodule.service.model.Task;
import com.example.multimodule.service.model.TaskRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(TasksController.class)
public class TasksControllerTest {

	@MockBean
	private TaskService taskService;

	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;


	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(new TasksController(taskService)).build();
		when(taskService.createTask(any(TaskRequest.class))).thenAnswer(invocation -> {
			TaskRequest taskRequest = invocation.getArgument(0);
			Task task = new Task();
			task.setId(1L);
			task.setTitle(taskRequest.getTitle());
			task.setDueDate(taskRequest.getDueDate());
			task.setStatus(taskRequest.getStatus());
			return task;
		});
	}

	@Test
	void testCreateTask() throws Exception {
		TaskRequest task = new TaskRequest();
		task.setTitle("Test Task");
		task.setDueDate(LocalDate.now());
		task.setStatus(Task.Status.OPEN);
		mockMvc.perform(post("/tasks/create")
		    .contentType(MediaType.APPLICATION_JSON)
		    .content(objectMapper.writeValueAsString(task)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
	}

	@Test
	void testDeleteTask() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/tasks/delete/{id}", 1L))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}


}
