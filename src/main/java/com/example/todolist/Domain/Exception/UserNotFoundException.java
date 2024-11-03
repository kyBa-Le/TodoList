package com.example.todolist.Domain.Exception;

public class UserNotFoundException extends HasErrorCodeException{
    public UserNotFoundException(String fieldName, String message) {
        super(fieldName, "not found", message);
    }
}
