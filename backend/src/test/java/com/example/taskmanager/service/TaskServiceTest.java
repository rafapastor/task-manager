package com.example.taskmanager.service;

import com.example.taskmanager.dto.CreateTaskDTO;
import com.example.taskmanager.dto.UpdateTaskDTO;
import com.example.taskmanager.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        taskService.getAllTasks().clear();
        taskService.saveTasks();
    }

    @Test
    public void testCreateTask() {
        CreateTaskDTO createTaskDTO = new CreateTaskDTO();
        createTaskDTO.setTitle("New Task");
        createTaskDTO.setDescription("This is a new task");
        createTaskDTO.setDueDate("2024-12-31");

        Task task = taskService.createTask(createTaskDTO);

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

        List<Task> tasks = taskService.getAllTasks();
        assertEquals(2, tasks.size());
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

        Task updatedTask = taskService.updateTask(createdTask.getId(), updateTaskDTO);

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
        taskService.deleteTask(createdTask.getId());

        List<Task> tasks = taskService.getAllTasks();
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void testUpdateTaskWithPastDueDateForCompletedStatus() {
        CreateTaskDTO createTaskDTO = new CreateTaskDTO();
        createTaskDTO.setTitle("New Task");
        createTaskDTO.setDescription("This is a new task");
        createTaskDTO.setDueDate("2024-12-31");

        Task createdTask = taskService.createTask(createTaskDTO);

        UpdateTaskDTO updateTaskDTO = new UpdateTaskDTO();
        updateTaskDTO.setTitle("Updated Task");
        updateTaskDTO.setDescription("This task has been updated");
        updateTaskDTO.setDueDate("2023-12-31");
        updateTaskDTO.setStatus("completed");

        Task updatedTask = taskService.updateTask(createdTask.getId(), updateTaskDTO);

        assertNotNull(updatedTask);
        assertEquals("Updated Task", updatedTask.getTitle());
        assertEquals("This task has been updated", updatedTask.getDescription());
        assertEquals("2023-12-31", updatedTask.getDueDate());
        assertEquals("completed", updatedTask.getStatus());
    }
}
