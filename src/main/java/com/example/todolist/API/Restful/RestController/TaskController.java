package com.example.todolist.API.Restful.RestController;

import com.example.todolist.API.Restful.Dto.Base.Response;
import com.example.todolist.API.Restful.Dto.Request.CreateTaskRequest;
import com.example.todolist.Domain.Entity.Task;
import com.example.todolist.Domain.Service.TaskService;
import com.example.todolist.Infrastructure.Repository.TaskRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

    @PostMapping
    public ResponseEntity<Response> createTask(@RequestBody @Valid CreateTaskRequest createTaskRequest, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        String userId = (String) session.getAttribute("user_id");

        Task task = taskService.createTask(
                createTaskRequest.title(),
                createTaskRequest.description(),
                userId
        );

        taskRepository.save(task);
        return ResponseEntity.status(200).body(new Response("Task Created"));
    }
}