package com.example.todolist.Domain.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Inheritance
public class Task extends BaseEntity{
    @NotNull
    @NotBlank
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    public Task(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }

    protected Task() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }
}
