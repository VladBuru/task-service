package com.task_service.task_service.service;

import com.task_service.task_service.model.Task;
import com.task_service.task_service.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task createdTask) {
        return taskRepository.save(createdTask);
    }
}
