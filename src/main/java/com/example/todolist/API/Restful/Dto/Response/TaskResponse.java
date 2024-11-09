package com.example.todolist.API.Restful.Dto.Response;

import com.example.todolist.Domain.Entity.User;

public record TaskResponse(
        String id,
        String title,
        String description,
        String userId,
        User user
) {
}
