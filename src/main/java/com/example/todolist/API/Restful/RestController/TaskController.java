package com.example.todolist.API.Restful.RestController;

import com.example.todolist.API.Restful.Dto.Base.Response;
import com.example.todolist.API.Restful.Dto.Base.ResponseWithData;
import com.example.todolist.API.Restful.Dto.Request.CreateTaskRequest;
import com.example.todolist.API.Restful.Dto.Request.UpdateTaskRequest;
import com.example.todolist.API.Restful.Dto.Response.NewTaskResponse;
import com.example.todolist.API.Restful.Dto.Response.TaskResponse;
import com.example.todolist.Domain.Exception.TaskAlreadyCompletedException;
import com.example.todolist.Domain.Exception.TaskNotFoundException;
import com.example.todolist.Infrastructure.EmailService.EmailNotificationService;
import com.example.todolist.Domain.Service.TaskService;
import com.example.todolist.Infrastructure.Repository.TaskRepository;
import com.example.todolist.Infrastructure.Auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
    @Autowired
    private EmailNotificationService emailSenderService;

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody @Valid CreateTaskRequest createTaskRequest, HttpServletRequest httpServletRequest) {
        var userId = authService.getSession(httpServletRequest).userId();
        var task = taskService.createTask(
            userId,
            createTaskRequest.title(),
            createTaskRequest.description()
        );

        taskRepository.save(task);

        var response = new ResponseWithData<>(
            "Create task successful",
            new NewTaskResponse(task.getId())
        );
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable("id") String id) {
        var task = taskRepository.findById(id);

        if (task == null) {
            return ResponseEntity.status(404).body(new Response("Task not found!"));
        }

        TaskResponse taskResponse = TaskResponse.FromTaskToTaskResponse(task);
        return ResponseEntity.status(200).body(new ResponseWithData<>("",taskResponse));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllTasks(@RequestParam(defaultValue = "0", name = "page") Integer pageNo,
                                         @RequestParam(defaultValue = "10", name = "size") Integer pageSize) {
        var paging = PageRequest.of(pageNo, pageSize);
        var tasks = taskRepository.findAll(paging);

        var taskResponses = TaskResponse.FromTasksToTaskResponses(tasks.getContent());

        Page<TaskResponse> pageTaskResponses = new PageImpl<>(taskResponses, paging, tasks.getTotalElements());
        return ResponseEntity.status(200).body(new ResponseWithData<>("",pageTaskResponses));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getTaskByTitle(@RequestParam(defaultValue = "", name="title") String title,
                                            @RequestParam(defaultValue = "0", name = "page") Integer pageNo,
                                            @RequestParam(defaultValue = "10", name = "size") Integer pageSize) {
        var paging = PageRequest.of(pageNo, pageSize);
        var tasks = taskRepository.findByTitleContaining(title, paging);

        var taskResponses = TaskResponse.FromTasksToTaskResponses(tasks.getContent());

        Page<TaskResponse> pageTaskResponses = new PageImpl<>(taskResponses, paging, tasks.getTotalElements());
        return ResponseEntity.status(200).body(new ResponseWithData<>("",pageTaskResponses));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<?> completeTask(@PathVariable("id") String id, HttpServletRequest request) {
        var session = authService.getSession(request);

        try {
            var task = taskService.completeTask(id, session.userId());
            taskRepository.save(task);

            emailSenderService.sendEmailForTaskCompleting(task.user.getEmail(), task.getTitle());

            TaskResponse taskResponse = TaskResponse.FromTaskToTaskResponse(task);
            return ResponseEntity.status(200).body(new ResponseWithData<>("Task is done", taskResponse));

        } catch (TaskNotFoundException e) {
            return ResponseEntity.status(404).body(new Response(e.getMessage()));
        } catch (TaskAlreadyCompletedException e) {
            return ResponseEntity.status(400).body(new Response(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable("id") String id,
                                        @RequestBody @Valid UpdateTaskRequest updateTaskRequest,
                                        HttpServletRequest request) {
        var session = authService.getSession(request);

        var task = taskService.updateTask(id,
                session.userId(),
                updateTaskRequest.title(),
                updateTaskRequest.description()
        );

        taskRepository.save(task);

        var taskResponse = TaskResponse.FromTaskToTaskResponse(task);
        return ResponseEntity.status(200).body(new ResponseWithData<>("Task updated successfully", taskResponse));
    }
}