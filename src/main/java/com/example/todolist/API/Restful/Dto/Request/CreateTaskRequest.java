package com.example.todolist.API.Restful.Dto.Request;

import com.example.todolist.Domain.Validation.TaskValidation.TitleRule;

public record CreateTaskRequest (
        @TitleRule
        String title,
        String description
) {
}
