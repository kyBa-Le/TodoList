package com.example.todolist.Domain.Service;

import com.example.todolist.Domain.Entity.User;
import com.example.todolist.Domain.Exception.DuplicatedUserEmailException;
import com.example.todolist.Domain.Exception.DuplicatedUsernameException;
import com.example.todolist.Infrastructure.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(String username, String password, String email, String phoneNumber) {
        if (userRepository.findByUsername(username) != null) {
            throw new DuplicatedUsernameException("username", "username already exists");
        }

        if (userRepository.findByEmail(email) != null) {
            throw new DuplicatedUserEmailException("email", "email already exists");
        }

        return new User(username, password, email, phoneNumber);
    }

}
