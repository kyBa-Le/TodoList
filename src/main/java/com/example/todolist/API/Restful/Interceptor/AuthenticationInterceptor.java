package com.example.todolist.API.Restful.Interceptor;

import com.example.todolist.Infrastructure.Auth.AuthService;
import com.example.todolist.Infrastructure.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.Arrays;
import java.util.List;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private static final List<String> acceptedPaths = Arrays.asList("/signup", "/sign-in");

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;

    @Override
    public boolean preHandle(@NonNull  HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        var userId = authService.getSession(request).userId();
        var requestPath = request.getServletPath();

        var isAuthenticated = userRepository.findUserById(userId) != null;
        var isAcceptedPath = acceptedPaths.contains(requestPath);

        return (isAuthenticated || isAcceptedPath);
    }
}
