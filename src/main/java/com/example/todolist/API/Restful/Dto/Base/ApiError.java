package com.example.todolist.API.Restful.Dto.Base;

import com.example.todolist.Domain.Exception.HasErrorCodeException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record ApiError(String message, List<Error> errors) {

    public static ApiError FromHasErrorCodeExceptionToApiError(String fieldName, HasErrorCodeException e) {
        var errors = new ArrayList<Error>();
        errors.add(new Error(fieldName, List.of(new ErrorDetail(e.code, e.getMessage()))));
        return new ApiError("An error has occur", errors);
    }

    public static ApiError FromMethodArgumentNotValidExceptionToApiError(MethodArgumentNotValidException e) {
        var errors = e.getBindingResult().getFieldErrors();
        var es = new ArrayList<Error>();
        var errorsGroupedByField = errors.stream().collect(Collectors.groupingBy(FieldError::getField));
        errorsGroupedByField.forEach((field, fieldErrors) -> {
            var errorDetails = new ArrayList<ErrorDetail>();
            for (var error : fieldErrors) {
                errorDetails.add(new ErrorDetail(error.getCode(), error.getDefaultMessage()));
            }
            es.add(new Error(field, errorDetails));
        });
        return new ApiError("Validation error", es);
    }
}
