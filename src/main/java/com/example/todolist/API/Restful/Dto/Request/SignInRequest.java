package com.example.todolist.API.Restful.Dto.Request;

import com.example.todolist.Domain.Validation.UserValidation.EmailRule;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SignInRequest(
        @EmailRule
        String email,
        @NotNull
        @NotBlank
        String password) {
}