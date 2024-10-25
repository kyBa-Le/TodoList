package com.example.todolist.API.Restful.HandlingException;

import java.util.Date;

public class APIError {
    private String field;
    private String code;
    private String message;
    private Date timestamp;

    public APIError(String code, String message, Date timestamp) {
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
    }
    public APIError(String field, String code, String message, Date timestamp) {
        this.field = field;
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
