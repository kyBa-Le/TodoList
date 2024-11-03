package com.example.todolist.API.Restful.RestController;

import com.example.todolist.API.Restful.Dto.Base.ApiError;
import com.example.todolist.API.Restful.Dto.Base.ResponseWithData;
import com.example.todolist.API.Restful.Dto.Request.CreateTaskRequest;
import com.example.todolist.API.Restful.Dto.Response.TaskResponse;
import com.example.todolist.Domain.Entity.Task;
import com.example.todolist.Domain.Exception.UserNotFoundException;
import com.example.todolist.Domain.Service.TaskService;
import com.example.todolist.Infrastructure.Repository.TaskRepository;
import com.example.todolist.Infrastructure.RestfulService.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody @Valid CreateTaskRequest createTaskRequest, HttpServletRequest httpServletRequest) {
        String userId = authService.getUserIdFromSession(httpServletRequest);

        try {
            Task task = taskService.createTask(
                    userId,
                    createTaskRequest.title(),
                    createTaskRequest.description()
            );

            taskRepository.save(task);
            return ResponseEntity.status(201).
                    body(new ResponseWithData<>("Create task successful", new TaskResponse(task.getUserId())));
        } catch (UserNotFoundException e) {
            ApiError apiError = ApiError.FromHasErrorCodeExceptionToApiError("session", e);
            return ResponseEntity.status(401).body(apiError);
        }
    }
}