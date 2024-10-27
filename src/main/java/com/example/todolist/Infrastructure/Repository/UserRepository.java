package com.example.todolist.Infrastructure.Repository;

import com.example.todolist.Domain.Entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {
    void save(User user);
    User findByEmail(String email);
    User findByUsername(String username);
    User findUserById(String id);
}
