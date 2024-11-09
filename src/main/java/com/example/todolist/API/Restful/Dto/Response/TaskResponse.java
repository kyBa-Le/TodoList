package com.example.todolist.API.Restful.Dto.Response;

public record TaskResponse(
        String id,
        String title,
        String description,
        String userId
) {
}
