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

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskDto createdTask) {
        Task newTask = taskService.createTask(taskMapper.convertToEntity(createdTask));
        TaskDto response = taskMapper.convertToDto(newTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        List<TaskDto> response = taskMapper.convertToListDto(tasks);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
        Task task = taskService.getTask(id);
        TaskDto response = taskMapper.convertToDto(task);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/users/{id}/tasks")
    public ResponseEntity<List<TaskDto>> getTasksForUser(@PathVariable Long id) {
        List<Task> tasks = taskService.getTasksByUserId(id);
        List<TaskDto> response = taskMapper.convertToListDto(tasks);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> updateTask(@Valid @RequestBody TaskDto taskDto,
                                              @PathVariable Long id) {
        Task task = taskMapper.convertToEntity(taskDto);
        Task updatedTask = taskService.updateTask(task, id);
        TaskDto response = taskMapper.convertToDto(updatedTask);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
