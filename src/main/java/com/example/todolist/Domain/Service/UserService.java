package com.example.todolist.Domain.Service;

import com.example.todolist.API.Restful.HandlingException.ExistingValueException;
import com.example.todolist.Domain.Entity.User;
import com.example.todolist.Infrastructure.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User signUp(String username, String password, String email, String phoneNumber){
        if(userRepository.findByUsername(username) != null) {
            throw new ExistingValueException("Username is already in use", "username");
        }else if(userRepository.findByEmail(email) != null) {
            throw new ExistingValueException("Email is already in use", "email");
        }
        User user = new User(username, password, email, phoneNumber);
        return userRepository.save(user);
    }

}
