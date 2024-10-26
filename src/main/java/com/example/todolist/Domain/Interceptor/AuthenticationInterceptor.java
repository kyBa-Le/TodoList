package com.example.todolist.Domain.Interceptor;

import com.example.todolist.Infrastructure.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.Arrays;
import java.util.List;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private UserRepository userRepository;
    private final List<String> acceptedPaths = Arrays.asList("/signup", "/login");


    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        HttpSession session = request.getSession(false);
        String requestPath = request.getServletPath();

        var isAuthenticated = (session != null && userRepository.findUserById( (String) session.getAttribute("user_id")) != null);
        var isAcceptedPath = acceptedPaths.contains(requestPath);

        return (isAuthenticated || isAcceptedPath);
    }
}
