package com.task_service.task_service.dto;

import com.task_service.task_service.util.TaskStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    @NotNull
    @Size(min = 2, max = 255)
    private String title;

    @Size(min = 2, max = 255)
    private String description;

    private TaskStatus status = TaskStatus.NEW;
}
