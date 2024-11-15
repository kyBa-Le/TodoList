package com.example.todolist.API.Restful.Dto.Request;

public record UpdateTaskRequest(
     String title,
     String description,
     boolean changeStatus
) {
}