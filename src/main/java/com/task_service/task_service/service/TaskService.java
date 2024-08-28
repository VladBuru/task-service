package com.task_service.task_service.service;

import com.task_service.task_service.model.Task;
import com.task_service.task_service.repository.TaskRepository;
import com.task_service.task_service.util.exception.TaskNotFoundException;
import com.task_service.task_service.util.exception.TaskTitleAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public Task createTask(Task createdTask) {
        return taskRepository.save(createdTask);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTask(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    public List<Task> getTasksByUserId(Long id) {
        return taskRepository.findByUserId(id);
    }

    @Transactional
    public Task updateTask(Task task, Long id) {
        Task updatedTask = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        if (taskRepository.existsByTitle(task.getTitle()) && !updatedTask.getTitle().equals(task.getTitle())) {
            throw  new TaskTitleAlreadyExistsException(task.getTitle());
        }
        updatedTask.setTitle(task.getTitle());
        updatedTask.setDescription(task.getDescription());
        updatedTask.setStatus(task.getStatus());
        updatedTask.setUserId(task.getUserId());
        return updatedTask;
    }

    @Transactional
    public void deleteTask(Long id) {
        Task deletedTask = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        taskRepository.delete(deletedTask);
    }
}
