package com.dadadarbar.web.controller;

import com.dadadarbar.web.dto.ApiResponse;
import com.dadadarbar.web.dto.LoginRequest;
import com.dadadarbar.web.dto.LoginResponse;
import com.dadadarbar.web.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody @Valid LoginRequest request) throws InvalidCredentialsException {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(
                ApiResponse.<LoginResponse>builder()
                        .success(true)
                        .message("Login Successful")
                        .data(response)
                        .build()
        );
    }
}
