package com.example.todolist.Domain.Service;

import com.example.todolist.Domain.Entity.Task;
import com.example.todolist.Domain.Entity.User;
import com.example.todolist.Infrastructure.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private UserRepository userRepository;
    public Task createTask(String title, String description, String userId) {
        User user = userRepository.findUserById(userId);
        return new Task(title, description, user);
    }
}