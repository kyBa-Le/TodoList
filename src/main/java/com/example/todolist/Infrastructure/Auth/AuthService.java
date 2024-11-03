package com.example.todolist.Infrastructure.Auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class AuthService {
    public String getAttributeFromSession(HttpServletRequest request, Boolean isNewSession, String attribute) {
        if (request.getSession(isNewSession) == null) {
            return null;
        }
        return request.getSession(isNewSession).getAttribute(attribute).toString();
    }

    public void setAttributeInSession(HttpServletRequest request, Boolean isNewSession, String attribute, String value) {
        HttpSession session = request.getSession(isNewSession);
        session.setAttribute(attribute, value);
    }
}
