package com.example.todolist.API.Restful.Dto.Request;

import com.example.todolist.Domain.Validation.TaskValidation.TitleRule;

public record UpdateTaskRequest(
     @TitleRule
     String title,
     String description
) {
}