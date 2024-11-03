package com.example.todolist.Domain.Entity;

import com.example.todolist.Domain.Validation.TaskValidation.TitleRule;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Inheritance
public class Task extends BaseEntity{
    @TitleRule
    private String title;

    private String description;

    @NotNull
    @Column(name = "user_id")
    private String userId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    public User user;

    public Task(String title, String description, String userId) {
        this.title = title;
        this.description = description;
        this.userId = userId;
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

    public String getUserId() {
        return this.userId;
    }
}
