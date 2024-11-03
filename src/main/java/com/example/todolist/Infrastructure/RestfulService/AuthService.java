package com.example.todolist.Infrastructure.RestfulService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthService {
    public String getUserIdFromSession(HttpServletRequest request) {
        if (request.getSession().getAttribute("user_id") == null) {
            return null;
        }
        return request.getSession(false).getAttribute("user_id").toString();
    }
}
