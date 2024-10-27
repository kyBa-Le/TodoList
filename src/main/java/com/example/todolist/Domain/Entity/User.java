package com.example.todolist.Domain.Entity;

import com.example.todolist.Domain.Validation.UserValidation.EmailRule;
import com.example.todolist.Domain.Validation.UserValidation.PasswordRule;
import com.example.todolist.Domain.Validation.UserValidation.PhoneRule;
import com.example.todolist.Domain.Validation.UserValidation.UsernameRule;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User extends BaseEntity {
    @Column(unique = true, nullable = false)
    @UsernameRule
    private String username;
    
    @Column(nullable = false)
    @PasswordRule
    private String password;

    @Column(nullable = false, unique = true)
    @EmailRule
    private String email;

    @PhoneRule
    private String phone;

    protected User() {}
    public User(String username, String password, String email, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
