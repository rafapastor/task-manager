package com.example.taskmanager.controller;

import com.example.taskmanager.dto.CreateTaskDTO;
import com.example.taskmanager.dto.UpdateTaskDTO;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TaskService taskService;

    private String baseUrl;

    @BeforeEach
    public void setUp() {
        baseUrl = "http://localhost:" + port + "/tasks";
        taskService.getAllTasks().clear();
    }

    @Test
    public void testCreateTask() {
        CreateTaskDTO createTaskDTO = new CreateTaskDTO();
        createTaskDTO.setTitle("New Task");
        createTaskDTO.setDescription("This is a new task");
        createTaskDTO.setDueDate("2024-12-31");

        ResponseEntity<Task> response = restTemplate.postForEntity(baseUrl, createTaskDTO, Task.class);

        assertEquals(200, response.getStatusCode().value());
        Task task = response.getBody();
        assertNotNull(task);
        assertEquals("New Task", task.getTitle());
        assertEquals("This is a new task", task.getDescription());
        assertEquals("2024-12-31", task.getDueDate());
        assertEquals("pending", task.getStatus());
    }

    @Test
    public void testGetAllTasks() {
        CreateTaskDTO createTaskDTO1 = new CreateTaskDTO();
        createTaskDTO1.setTitle("Task 1");
        createTaskDTO1.setDescription("Description 1");
        createTaskDTO1.setDueDate("2024-12-31");

        CreateTaskDTO createTaskDTO2 = new CreateTaskDTO();
        createTaskDTO2.setTitle("Task 2");
        createTaskDTO2.setDescription("Description 2");
        createTaskDTO2.setDueDate("2025-01-01");

        taskService.createTask(createTaskDTO1);
        taskService.createTask(createTaskDTO2);

        ResponseEntity<Task[]> response = restTemplate.getForEntity(baseUrl, Task[].class);

        assertEquals(200, response.getStatusCode().value());
        Task[] tasks = response.getBody();
        assertNotNull(tasks);
        assertEquals(2, tasks.length);
    }

    @Test
    public void testUpdateTask() {
        CreateTaskDTO createTaskDTO = new CreateTaskDTO();
        createTaskDTO.setTitle("New Task");
        createTaskDTO.setDescription("This is a new task");
        createTaskDTO.setDueDate("2024-12-31");

        Task createdTask = taskService.createTask(createTaskDTO);

        UpdateTaskDTO updateTaskDTO = new UpdateTaskDTO();
        updateTaskDTO.setTitle("Updated Task");
        updateTaskDTO.setDescription("This task has been updated");
        updateTaskDTO.setDueDate("2025-01-01");
        updateTaskDTO.setStatus("completed");

        HttpEntity<UpdateTaskDTO> requestEntity = new HttpEntity<>(updateTaskDTO);
        ResponseEntity<Task> response = restTemplate.exchange(baseUrl + "/" + createdTask.getId(), HttpMethod.PUT,
                requestEntity, Task.class);

        assertEquals(200, response.getStatusCode().value());
        Task updatedTask = response.getBody();
        assertNotNull(updatedTask);
        assertEquals("Updated Task", updatedTask.getTitle());
        assertEquals("This task has been updated", updatedTask.getDescription());
        assertEquals("2025-01-01", updatedTask.getDueDate());
        assertEquals("completed", updatedTask.getStatus());
    }

    @Test
    public void testDeleteTask() {
        CreateTaskDTO createTaskDTO = new CreateTaskDTO();
        createTaskDTO.setTitle("New Task");
        createTaskDTO.setDescription("This is a new task");
        createTaskDTO.setDueDate("2024-12-31");

        Task createdTask = taskService.createTask(createTaskDTO);

        ResponseEntity<Void> response = restTemplate.exchange(baseUrl + "/" + createdTask.getId(), HttpMethod.DELETE,
                null, Void.class);

        assertEquals(204, response.getStatusCode().value());
        assertTrue(taskService.getAllTasks().isEmpty());
    }
}
