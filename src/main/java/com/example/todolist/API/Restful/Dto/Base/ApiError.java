package com.example.todolist.API.Restful.Dto.Base;

import com.example.todolist.Domain.Exception.HasErrorCodeException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

public record ApiError(String message, List<Error> errors) {

    public static ApiError FromHasErrorCodeExceptionToApiError(HasErrorCodeException e, String errorMessage) {
        var errors = new ArrayList<Error>();
        errors.add(new Error(e.fieldName, List.of(new ErrorDetail(e.code, e.getMessage()))));
        return new ApiError(errorMessage, errors);
    }

    public static ApiError FromMethodArgumentNotValidExceptionToApiError(MethodArgumentNotValidException e, String errorMessage) {
        var errors = new ArrayList<Error>();
        var errorDetails = new ArrayList<ErrorDetail>();

        String field = e.getBindingResult().getFieldErrors().getFirst().getField();
        for(FieldError error : e.getBindingResult().getFieldErrors()) {
            var errorDetail = new ErrorDetail(error.getField(), error.getDefaultMessage());
            if (field.equals(error.getField())) {
                errorDetails.add(errorDetail);
            } else {
                errors.add(new Error(field, errorDetails));
                errorDetails = new ArrayList<>();
                field = error.getField();
            }
        }
        errors.add(new Error(field, errorDetails));
        return new ApiError(errorMessage, errors);
    }
}
