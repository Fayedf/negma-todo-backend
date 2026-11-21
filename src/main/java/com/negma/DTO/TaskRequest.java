
package com.negma.DTO;

import com.negma.Enums.TaskPriority;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 120,
            message = "Title must not exceed 120 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 1000,
            message = "Description must not exceed 1000 characters")
    private String description;

    private Boolean completed;

    @NotNull(message = "Priority is required")
    private TaskPriority priority;

    @Future(message = "Due date must be in the future")
    private LocalDate dueDate;
}
