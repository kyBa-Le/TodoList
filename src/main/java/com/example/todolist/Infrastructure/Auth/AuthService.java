package com.example.todolist.Infrastructure.Auth;

import com.example.todolist.Infrastructure.Auth.Dto.UserSessionDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthService {

    private final String UserSessionKey = "userSession";

    /**
     * set new session and cookie
     * */
    public void newSession(HttpServletRequest request, UserSessionDto userSession) {
        var session = request.getSession(true);
        session.setAttribute(UserSessionKey, userSession);
    }

    /**
     * get existing session
     */
    public UserSessionDto getSession(HttpServletRequest request) {
        var session = request.getSession(false);
        if (session == null) {
            return null;
        }
        return (UserSessionDto) session.getAttribute(UserSessionKey);
    }
}
