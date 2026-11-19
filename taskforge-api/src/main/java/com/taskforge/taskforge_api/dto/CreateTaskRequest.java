package com.taskforge.taskforge_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateTaskRequest(
        @NotBlank(message = "title is required")
        @Size(max = 100, message = "title must be at most 100 characters")
        String title,

        @NotBlank(message = "type is required")
        @Pattern(regexp = "BUG|STORY|EPIC", message = "type must be BUG, STORY or EPIC")
        String type) {
}