package com.example.todolist.Infrastructure.Repository;

import com.example.todolist.Domain.Entity.Task;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaskRepository extends PagingAndSortingRepository<Task, String> {
    void save(Task task);
    Task findById(String id);
}