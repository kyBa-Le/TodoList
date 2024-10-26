package com.example.todolist.API.Restful.RestController;

import com.example.todolist.API.Restful.Dto.Request.SignUpRequest;
import com.example.todolist.API.Restful.Dto.Response.SignUpResponse;
import com.example.todolist.Domain.Service.UserService;
import com.example.todolist.Infrastructure.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpRequest signUpRequest, HttpServletRequest request) {
        var user = userService.createUser(
                signUpRequest.username(),
                signUpRequest.password(),
                signUpRequest.email(),
                signUpRequest.phone());
        userRepository.save(user);

        HttpSession session = request.getSession();
        session.setAttribute("user_id", user.getId());

        return ResponseEntity.status(201).body(new SignUpResponse());
    }
}
