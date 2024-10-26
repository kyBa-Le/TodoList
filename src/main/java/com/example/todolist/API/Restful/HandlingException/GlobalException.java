package com.example.todolist.API.Restful.HandlingException;

import com.example.todolist.API.Restful.Dto.Base.ApiError;
import com.example.todolist.Domain.Exception.HasErrorCodeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> invalidArgument(MethodArgumentNotValidException exception){
        ApiError apiError = ApiError.FromMethodArgumentNotValidExceptionToApiError(exception, "Validation Failed");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(value = HasErrorCodeException.class)
    public ResponseEntity<ApiError> duplicatedValue(HasErrorCodeException exception){
        ApiError apiError = ApiError.FromHasErrorCodeExceptionToApiError(exception, "Duplicated Value");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
    }
}
