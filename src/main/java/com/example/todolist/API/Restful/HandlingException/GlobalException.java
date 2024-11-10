package com.example.todolist.API.Restful.HandlingException;

import com.example.todolist.API.Restful.Dto.Base.ApiError;
import com.example.todolist.API.Restful.Dto.Base.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> invalidArgument(MethodArgumentNotValidException exception){
        ApiError apiError = ApiError.FromMethodArgumentNotValidExceptionToApiError(exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Response> badRequest(IllegalArgumentException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(exception.getMessage()));
    }
}
