package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                tasks = objectMapper.readValue(file, new TypeReference<List<Task>>() {});
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
        task.setId(tasks.size() + 1L); // Simple ID generation
        tasks.add(task);
        saveTasks();
        return task;
    }

    public Task updateTask(Long id, Task task) {
        Optional<Task> existingTaskOpt = tasks.stream().filter(t -> t.getId().equals(id)).findFirst();
        if (existingTaskOpt.isPresent()) {
            Task existingTask = existingTaskOpt.get();
            existingTask.setTitle(task.getTitle());
            existingTask.setDescription(task.getDescription());
            existingTask.setDueDate(task.getDueDate());
            existingTask.setCompleted(task.isCompleted());
            saveTasks();
            return existingTask;
        }
        return null;
    }

    public void deleteTask(Long id) {
        tasks.removeIf(t -> t.getId().equals(id));
        saveTasks();
    }
}
