package com.task_service.task_service.dto.mapper;

import com.task_service.task_service.dto.TaskDto;
import com.task_service.task_service.model.Task;
import com.task_service.task_service.util.TaskStatus;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@AllArgsConstructor
public class TaskMapper {

    private ModelMapper modelMapper;

    public TaskDto convertToDto(Task task) {
        TaskDto resultTask = modelMapper.map(task, TaskDto.class);
        resultTask.setStatus(task.getStatus().name());
        return resultTask;
    }

    public Task convertToEntity(TaskDto taskDto) {
        Task resultTask = modelMapper.map(taskDto, Task.class);
        resultTask.setStatus(TaskStatus.valueOf(taskDto.getStatus()));
        return resultTask;
    }

    public List<TaskDto> convertToListDto(List<Task> tasks) {
        return tasks.stream().map(task -> modelMapper.map(task, TaskDto.class)).toList();
    }
}
