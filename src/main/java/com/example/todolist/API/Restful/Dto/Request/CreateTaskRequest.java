package com.example.todolist.API.Restful.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTaskRequest (
        @NotNull
        @NotBlank
        String title,
        String description
) {
}
