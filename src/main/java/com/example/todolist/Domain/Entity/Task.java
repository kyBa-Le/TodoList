package com.example.todolist.Domain.Entity;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Task extends BaseEntity {
    private String title;
    private String description;
    @Column(name = "user_id")
    private String userId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false, insertable = false)
    private User user;

    public Task(){}

    public Task(User user, String title, String description) {
        this.user = user;
        this.title = title;
        this.description = description;
    }

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
        return userId;
    }

    public User getUser() {
        return user;
    }
}
