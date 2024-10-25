package com.example.todolist.API.Restful.RestController;

import com.example.todolist.API.Restful.DTO.Request.RequestSignUp;
import com.example.todolist.API.Restful.DTO.Response.SignUpResponse;
import com.example.todolist.Domain.Entity.User;
import com.example.todolist.Domain.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public SignUpResponse signUp(@RequestBody @Valid RequestSignUp requestSignUp, HttpServletResponse response, HttpServletRequest request) {
        User user = userService.signUp(requestSignUp.username(), requestSignUp.password(), requestSignUp.email(), requestSignUp.phone());
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        return new SignUpResponse(user.getUsername());
    }
    @GetMapping("/signup")
    public ResponseEntity<String> signUp(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok("You can access to the signup page");
    }
    @GetMapping("/login")
    public ResponseEntity<String> logIn(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok("This is login page!");
    }
}
