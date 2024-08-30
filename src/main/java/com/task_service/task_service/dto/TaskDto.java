package com.task_service.task_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "NEW|IN_PROGRESS|COMPLETED",
            message = "Task status should be NEW or IN_PROGRESS or COMPLETED")
    private String status;

    private Long userId;
}
