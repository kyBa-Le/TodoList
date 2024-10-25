package com.example.todolist.API.Restful.HandlingException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<List<APIError>> invalidArgument(MethodArgumentNotValidException exception){
        List<APIError> errors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach((error) -> {
            APIError apiError = new APIError(error.getField(), error.getCode(), error.getDefaultMessage(), new Date());
            errors.add(apiError);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = ExistingValueException.class)
    public ResponseEntity<List<APIError>> invalidArgument(ExistingValueException exception){
        List<APIError> errors = new ArrayList<>();
        APIError apiError = new APIError(exception.getFieldName(), exception.getCode(),exception.getMessage(), exception.getTimestamp());
        errors.add(apiError);
        return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
    }
}
