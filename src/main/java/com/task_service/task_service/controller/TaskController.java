package com.task_service.task_service.controller;

import com.task_service.task_service.dto.TaskDto;
import com.task_service.task_service.dto.mapper.TaskMapper;
import com.task_service.task_service.model.Task;
import com.task_service.task_service.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @PostMapping("/{id}")
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskDto createdTask,
                                              @PathVariable Long userId) {
        Task newTask = taskService.createTask(taskMapper.convertToEntity(createdTask), userId);
        TaskDto response = taskMapper.convertToDto(newTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
