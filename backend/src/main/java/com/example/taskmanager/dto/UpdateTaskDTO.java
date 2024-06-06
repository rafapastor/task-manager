package com.example.taskmanager.dto;

import com.example.taskmanager.validation.ValidDueDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UpdateTaskDTO {
    @NotBlank(message = "Title is mandatory")
    private String title;

    private String description;

    @NotNull(message = "Due date is mandatory")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Due date must be in the format YYYY-MM-DD")
    @ValidDueDate
    private String dueDate;

    @NotBlank(message = "Status is mandatory")
    private String status;

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
