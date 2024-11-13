package com.example.todolist.Domain.Exception;

public class TaskAlreadyCompletedException extends RuntimeException{
    public TaskAlreadyCompletedException(String message){
        super(message);
    }
}