package com.example.todolist.Domain.Service;

import com.example.todolist.Domain.Entity.Task;
import com.example.todolist.Domain.Entity.User;
import com.example.todolist.Domain.Exception.UserNotFoundException;
import com.example.todolist.Infrastructure.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private UserRepository userRepository;
    public Task createTask(String userId, String title, String description) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("user_id", "User not found");
        }

        return new Task(title, description, userId);
    }
}