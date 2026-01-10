package com.amaan.expense_tracker.controller;

import com.amaan.expense_tracker.controller.dto.LoginRequest;
import com.amaan.expense_tracker.controller.dto.LoginResponse;
import com.amaan.expense_tracker.controller.dto.RegisterRequest;
import com.amaan.expense_tracker.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterRequest registerRequest) {
        authService.register(
                registerRequest.getEmail(),
                registerRequest.getPassword(),
                registerRequest.getName()
        );
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        String token = authService.login(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        return new LoginResponse(token);
    }
}
