package com.task_service.task_service.controller;

import com.task_service.task_service.util.ErrorResponse;
import com.task_service.task_service.util.ErrorType;
import com.task_service.task_service.util.exception.TaskNotFoundException;
import com.task_service.task_service.util.exception.TaskTitleAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionControllerAdvice {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTaskNotFoundException(TaskNotFoundException ex,
                                                                     HttpServletRequest request) {
        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .error(ErrorType.RESOURCE_NOT_FOUND.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(TaskTitleAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleTaskTitleAlreadyExistsException(TaskTitleAlreadyExistsException ex,
                                                                               HttpServletRequest request) {
        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .error(ErrorType.NOT_UNIQUE_VALUE.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
