package com.example.taskmanager.service;

import com.example.taskmanager.dto.CreateTaskDTO;
import com.example.taskmanager.dto.UpdateTaskDTO;
import com.example.taskmanager.model.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private static final String FILE_PATH = "tasks.json";
    private List<Task> tasks = new ArrayList<>();

    public TaskService() {
        loadTasks();
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task createTask(CreateTaskDTO createTaskDTO) {
        Task task = new Task();
        task.setTitle(createTaskDTO.getTitle());
        task.setDescription(createTaskDTO.getDescription());
        task.setDueDate(createTaskDTO.getDueDate());
        task.setStatus("pending"); // Status is set to "pending" by default
        task.setId((long) (tasks.size() + 1));
        tasks.add(task);
        saveTasks();
        return task;
    }

    public Task updateTask(Long id, UpdateTaskDTO updateTaskDTO) {
        Task task = tasks.stream().filter(t -> t.getId().equals(id)).findFirst()
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(updateTaskDTO.getTitle());
        task.setDescription(updateTaskDTO.getDescription());
        task.setDueDate(updateTaskDTO.getDueDate());
        task.setStatus(updateTaskDTO.getStatus()); // Status is updated from DTO
        saveTasks();
        return task;
    }

    public void deleteTask(Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
        saveTasks();
    }

    private void loadTasks() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                tasks = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, Task.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void saveTasks() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(FILE_PATH), tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
