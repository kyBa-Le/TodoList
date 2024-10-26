package com.example.todolist.Domain.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    protected final String id = UUID.randomUUID().toString();

    public String getId() {
        return id;
    }
}
