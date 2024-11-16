package com.example.todolist.Domain.Service;

import com.example.todolist.Domain.Entity.TaskStatus;
import com.example.todolist.Domain.Entity.Task;
import com.example.todolist.Domain.Entity.User;
import com.example.todolist.Domain.Exception.TaskAlreadyCompletedException;
import com.example.todolist.Domain.Exception.TaskNotFoundException;
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
        var task = taskRepository.findByIdAndUserIdWithUser(taskId, userId);
        if (task == null) {
            throw new TaskNotFoundException("task not found");
        }

        if (task.getStatus() != TaskStatus.TODO) {
            throw new TaskAlreadyCompletedException("task already completed");
        }

        task.setStatus(TaskStatus.DONE);
        return task;
    }

    public Task editTaskContent(String id, String userId, String newTitle, String newDescription) {
        var task = taskRepository.findByIdAndUserIdWithUser(id, userId);
        if (task == null) {
            throw new TaskNotFoundException("task not found");
        }

        task.setTitle(newTitle);
        task.setDescription(newDescription);
        return task;
    }

    public void deleteTask(String taskId, String userId) {
        taskRepository.deleteByIdAndUserId(taskId, userId);
    }
}