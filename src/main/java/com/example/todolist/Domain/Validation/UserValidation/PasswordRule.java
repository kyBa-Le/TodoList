package com.example.todolist.Domain.Validation.UserValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@NotNull
@NotBlank
@Size(min = 6, message = "Password must be at least 6 characters")
@Constraint(validatedBy = {})
public @interface PasswordRule {
    String message() default "Password is invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}