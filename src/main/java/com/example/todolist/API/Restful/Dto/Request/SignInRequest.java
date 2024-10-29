package com.example.todolist.API.Restful.Dto.Request;

import com.example.todolist.Domain.Validation.UserValidation.EmailRule;
import com.example.todolist.Domain.Validation.UserValidation.PasswordRule;

public record SignInRequest(
        @EmailRule
        String email,
        @PasswordRule
        String password) {
}