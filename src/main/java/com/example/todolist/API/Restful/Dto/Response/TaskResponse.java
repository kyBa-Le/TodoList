package com.example.todolist.API.Restful.Dto.Response;

import com.example.todolist.Domain.Entity.Task;

import java.util.List;
import java.util.stream.Collectors;

public record TaskResponse(
        String id,
        String title,
        String description,
        String userId
) {
    public static List<TaskResponse> FromTasksToDtoResponses(List<Task> tasks) {
        return tasks.stream()
                .map(task -> new TaskResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getUserId()
                ))
                .collect(Collectors.toList());
    }
}
