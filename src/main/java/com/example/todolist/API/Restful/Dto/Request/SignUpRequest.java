package com.example.todolist.API.Restful.Dto.Request;

import com.example.todolist.Domain.Validation.UserValidation.EmailRule;
import com.example.todolist.Domain.Validation.UserValidation.PasswordRule;
import com.example.todolist.Domain.Validation.UserValidation.PhoneRule;
import com.example.todolist.Domain.Validation.UserValidation.UsernameRule;

public record SignUpRequest(
        @UsernameRule
        String username,
        @PasswordRule
        String password,
        @EmailRule
        String email,
        @PhoneRule
        String phone
) {
}
