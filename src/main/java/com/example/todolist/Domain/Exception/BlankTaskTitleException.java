package com.example.todolist.Domain.Exception;

public class BlankTaskTitleException extends HasErrorCodeException{
    public BlankTaskTitleException(String field , String message) {
        super(field, "NotBlank", message);
    }
}
