package com.example.todolist.Domain.Service;

import com.example.todolist.Domain.Entity.TaskStatus;
import com.example.todolist.Domain.Entity.Task;
import com.example.todolist.Domain.Entity.User;
import com.example.todolist.Domain.Exception.UserNotFoundException;
import com.example.todolist.Infrastructure.Repository.TaskRepository;
import com.example.todolist.Infrastructure.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(String userId, String title, String description) {
        User user = userRepository.findById(userId);
        if (user == null) {
            throw new UserNotFoundException("userId", "not found");
        }

        return new Task(title, description, userId);
    }

    public Task completeTask(String taskId, String userId) {
        var task = taskRepository.findByIdWithUser(taskId);
        if (task == null || !task.user.getId().equals(userId)) {
            throw new RuntimeException("task not found");
        }

        if (task.getStatus() != TaskStatus.TODO) {
            throw new RuntimeException("task already completed");
        }

        return task;
    }
}