
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
public class TaskResponse {

    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 120,
            message = "Title must not exceed 120 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min = 1, max = 1000,
            message = "Description must be between 1 and 1000 characters")
    private String description;

    @NotNull(message = "Completed status is required")
    private Boolean completed;

    @NotNull(message = "Priority is required")
    private TaskPriority priority;

    private LocalDate dueDate;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
