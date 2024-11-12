package com.example.todolist.API.Restful.Dto.Response;

import com.example.todolist.Domain.Entity.Task;
import java.util.List;
import java.util.stream.Collectors;

public record TaskResponse(
        String id,
        String title,
        String description,
        String status,
        String userId
) {

    public static List<TaskResponse> FromTasksToDtoResponses(List<Task> tasks) {
        return tasks.stream()
                .map(task -> new TaskResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getStatus().toString(),
                        task.getUserId()
                ))
                .collect(Collectors.toList());
    }

    public static TaskResponse toDtoResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus().toString(),
                task.getUserId()
        );
    }
}
