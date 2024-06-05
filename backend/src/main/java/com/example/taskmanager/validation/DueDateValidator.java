package com.example.taskmanager.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DueDateValidator implements ConstraintValidator<ValidDueDate, String> {

    @Override
    public void initialize(ValidDueDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(String dueDateField, ConstraintValidatorContext context) {
        if (dueDateField == null) {
            return false;
        }
        LocalDate dueDate = LocalDate.parse(dueDateField);
        return !dueDate.isBefore(LocalDate.now());
    }
}
