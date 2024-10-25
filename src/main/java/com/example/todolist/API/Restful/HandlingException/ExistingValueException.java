package com.example.todolist.API.Restful.HandlingException;

import java.util.Date;

public class ExistingValueException extends RuntimeException {
    private String fieldName;
    private String code = "ExistingValue";
    private final Date timestamp = new Date();
    public ExistingValueException(String message ,String fieldName, String code) {
        super(message);
        this.fieldName = fieldName;
        this.code = code;
    }
    public ExistingValueException(String message, String fieldName) {
        super(message);
        this.fieldName = fieldName;
    }
    public ExistingValueException(String message) {
        super(message);
    }
    public String getFieldName() {
        return fieldName;
    }
    public String getCode() {
        return code;
    }
    public Date getTimestamp() {
        return timestamp;
    }

}
