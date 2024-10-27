package com.example.todolist.Domain.Exception;

public class DuplicatedUsernameException extends HasErrorCodeException {
    public DuplicatedUsernameException(String fieldName, String message) {
        super(fieldName, "Duplicated", message);
    }
}