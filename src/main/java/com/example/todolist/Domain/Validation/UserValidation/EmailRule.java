package com.example.todolist.Domain.Validation.UserValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@NotNull
@NotBlank
@Email(message = "Email must be written in format example@email.com")
@Constraint(validatedBy = {})
public @interface EmailRule {
    String message() default "Email address is in valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}