package com.example.todolist.API.Restful.RestController;

import com.example.todolist.API.Restful.Dto.Base.ApiError;
import com.example.todolist.API.Restful.Dto.Base.Response;
import com.example.todolist.API.Restful.Dto.Base.ResponseWithData;
import com.example.todolist.API.Restful.Dto.Request.SignInRequest;
import com.example.todolist.API.Restful.Dto.Request.SignUpRequest;
import com.example.todolist.API.Restful.Dto.Response.SignInResponse;
import com.example.todolist.Domain.Entity.User;
import com.example.todolist.Domain.Exception.DuplicatedUserEmailException;
import com.example.todolist.Domain.Exception.DuplicatedUsernameException;
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
        try {
            var user = userService.createUser(
                    signUpRequest.username(),
                    signUpRequest.password(),
                    signUpRequest.email(),
                    signUpRequest.phone());
            userRepository.save(user);

            HttpSession session = request.getSession();
            session.setAttribute("user_id", user.getId());

            return ResponseEntity.status(201).body(new Response("Sign up successful"));

        } catch (DuplicatedUsernameException e) {
            ApiError error = ApiError.FromHasErrorCodeExceptionToApiError("username", e);
            return ResponseEntity.badRequest().body(error);

        } catch (DuplicatedUserEmailException e) {
            ApiError error = ApiError.FromHasErrorCodeExceptionToApiError("email", e);
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody @Valid SignInRequest signInRequest, HttpServletRequest request) {
        User user = userRepository.findUserByEmailAndPassword(signInRequest.email(), signInRequest.password());
        if (user == null) {
            return ResponseEntity.badRequest().body(new Response("Sign in failed"));
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("user_id", user.getId());

            return ResponseEntity.status(201).body(new ResponseWithData<>("Sign in successful",new SignInResponse(user.getId())));
        }
    }
}
