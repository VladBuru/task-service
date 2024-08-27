package com.task_service.task_service.dto;

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

    @NotNull
    private String status;

    private Long userId;
}
