package com.example.todolist.API.Restful.RestController;

import com.example.todolist.API.Restful.Dto.Base.Response;
import com.example.todolist.API.Restful.Dto.Base.ResponseWithData;
import com.example.todolist.API.Restful.Dto.Request.CreateTaskRequest;
import com.example.todolist.API.Restful.Dto.Response.TaskResponse;
import com.example.todolist.Domain.Entity.Task;
import com.example.todolist.Domain.Service.TaskService;
import com.example.todolist.Infrastructure.Repository.TaskRepository;
import com.example.todolist.Infrastructure.Auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        var userId = authService.getSession(httpServletRequest).userId();
        var task = taskService.createTask(
            userId,
            createTaskRequest.title(),
            createTaskRequest.description()
        );

        taskRepository.save(task);

        var res = new ResponseWithData<>(
            "Create task successful",
            new TaskResponse(task.getUserId())
        );
        return ResponseEntity.status(201).body(res);
    }

    @GetMapping
    public ResponseEntity<?> getTaskById(@RequestParam("id") String taskId) {
        Task task = taskRepository.findById(taskId);
        if (task == null) {
            return ResponseEntity.status(404).body(new Response("Task not found!"));
        }

        return ResponseEntity.status(200).body(task);
    }
}