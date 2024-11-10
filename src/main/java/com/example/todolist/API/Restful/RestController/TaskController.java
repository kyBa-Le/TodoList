package com.example.todolist.API.Restful.RestController;

import com.example.todolist.API.Restful.Dto.Base.Response;
import com.example.todolist.API.Restful.Dto.Base.ResponseWithData;
import com.example.todolist.API.Restful.Dto.Request.CreateTaskRequest;
import com.example.todolist.API.Restful.Dto.Response.NewTaskResponse;
import com.example.todolist.API.Restful.Dto.Response.TaskResponse;
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

        TaskResponse taskResponse = new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getUserId()
        );
        return ResponseEntity.status(200).body(new ResponseWithData<>("",taskResponse));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllTasks(@RequestParam(defaultValue = "0", name = "page") Integer pageNo,
                                         @RequestParam(defaultValue = "10", name = "size") Integer pageSize) {
        var paging = PageRequest.of(pageNo, pageSize);
        var tasks = taskRepository.findAll(paging);

        var taskResponses = TaskResponse.FromTasksToDtoResponses(tasks.getContent());

        Page<TaskResponse> pageTaskResponses = new PageImpl<>(taskResponses, paging, tasks.getTotalElements());
        return ResponseEntity.status(200).body(new ResponseWithData<>("",pageTaskResponses));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getTaskByTitle(@RequestParam(defaultValue = "", name="title") String title,
                                            @RequestParam(defaultValue = "0", name = "page") Integer pageNo,
                                            @RequestParam(defaultValue = "10", name = "size") Integer pageSize) {
        var paging = PageRequest.of(pageNo, pageSize);
        var tasks = taskRepository.findTaskByTitleContaining(title, paging);

        var taskResponses = TaskResponse.FromTasksToDtoResponses(tasks.getContent());

        Page<TaskResponse> pageTaskResponses = new PageImpl<>(taskResponses, paging, tasks.getTotalElements());
        return ResponseEntity.status(200).body(new ResponseWithData<>("",pageTaskResponses));
    }
}