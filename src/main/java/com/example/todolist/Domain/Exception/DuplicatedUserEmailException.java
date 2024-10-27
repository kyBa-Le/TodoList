package com.example.todolist.Domain.Exception;

public class DuplicatedUserEmailException extends HasErrorCodeException{
    public DuplicatedUserEmailException(String fieldName, String message) {
        super(fieldName, "Duplicated", message);
    }
}