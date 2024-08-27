package com.task_service.task_service.dto.mapper;

import com.task_service.task_service.dto.TaskDto;
import com.task_service.task_service.model.Task;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
public class TaskMapper {

    private ModelMapper modelMapper;

    public TaskDto convertToDto(Task task) {
        return modelMapper.map(task, TaskDto.class);
    }

    public Task convertToEntity(TaskDto taskDto) {
        return modelMapper.map(taskDto, Task.class);
    }
}
