package com.task_service.task_service.util.exception;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TaskTitleAlreadyExistsException extends RuntimeException {
    public TaskTitleAlreadyExistsException(String title) {
        super("Title already exists: " + title);
    }
}
