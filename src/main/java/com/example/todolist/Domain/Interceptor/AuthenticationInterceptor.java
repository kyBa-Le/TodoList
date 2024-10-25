package com.example.todolist.Domain.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

public class AuthenticationInterceptor implements HandlerInterceptor {
    private final List<String> acceptedPaths = Arrays.asList("/signup", "/login");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        String requestPath = request.getServletPath();
        if(session != null || acceptedPaths.contains(requestPath)) {
            return true;
        }else{
            response.sendRedirect("/login");
            return false;
        }
    }
}
