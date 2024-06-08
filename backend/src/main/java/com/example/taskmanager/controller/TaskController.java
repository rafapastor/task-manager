package com.example.taskmanager.controller;

import com.example.taskmanager.dto.CreateTaskDTO;
import com.example.taskmanager.dto.TaskResponseDTO;
import com.example.taskmanager.dto.UpdateTaskDTO;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:8081") // Allow CORS for frontend
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<TaskResponseDTO> getAllTasks() {
        return taskService.getAllTasks().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@Validated @RequestBody CreateTaskDTO createTaskDTO) {
        Task task = taskService.createTask(createTaskDTO);
        return ResponseEntity.ok(convertToDto(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id,
            @Validated @RequestBody UpdateTaskDTO updateTaskDTO) {
        Task task = taskService.updateTask(id, updateTaskDTO);
        return ResponseEntity.ok(convertToDto(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    private TaskResponseDTO convertToDto(Task task) {
        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setId(task.getId());
        taskResponseDTO.setTitle(task.getTitle());
        taskResponseDTO.setDescription(task.getDescription());
        taskResponseDTO.setDueDate(task.getDueDate());
        taskResponseDTO.setStatus(task.getStatus());
        return taskResponseDTO;
    }
}
