package com.example.todolist.Infrastructure.Repository;

import com.example.todolist.Domain.Entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {
    public User save(User user);
    public User findByEmail(String email);
    public User findByUsername(String username);
}
