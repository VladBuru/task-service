package com.task_service.task_service.util.exception;

public class TaskTitleAlreadyExistsException extends RuntimeException {
    public TaskTitleAlreadyExistsException(String title) {
        super("Title already exists: " + title);
    }
}
