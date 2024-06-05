package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final String filePath = "tasks.json";
    private List<Task> tasks = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    private void loadTasks() {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                tasks = objectMapper.readValue(file, new TypeReference<List<Task>>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveTasks() {
        try {
            objectMapper.writeValue(new File(filePath), tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task saveTask(Task task) {
        task.setId((long) (tasks.size() + 1)); // Simple ID generation
        task.setStatus("pending");
        tasks.add(task);
        saveTasks();
        return task;
    }

    public Task updateTask(Long id, Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id)) {
                tasks.set(i, task);
                saveTasks();
                return task;
            }
        }
        return null;
    }

    public void deleteTask(Long id) {
        tasks.removeIf(t -> t.getId().equals(id));
        saveTasks();
    }
}
