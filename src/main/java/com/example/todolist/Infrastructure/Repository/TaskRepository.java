package com.example.todolist.Infrastructure.Repository;

import com.example.todolist.Domain.Entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaskRepository extends PagingAndSortingRepository<Task, String> {
    void save(Task task);
    Task findById(String id);
    Page<Task> findByTitleContaining(String title, Pageable pageable);

    @Query("select t from Task t  join fetch t.user where t.id = :id")
    Task findByIdWithUser(String id);
}