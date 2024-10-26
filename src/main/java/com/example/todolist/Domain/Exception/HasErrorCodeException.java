package com.example.todolist.Domain.Exception;

public abstract class HasErrorCodeException extends RuntimeException {
    public final String fieldName;
    public final String code;

    public HasErrorCodeException(String fieldName, String code, String message) {
        super(message);
        this.fieldName = fieldName;
        this.code = code;
    }
}