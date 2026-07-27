package com.dadadarbar.web.service;

import com.dadadarbar.web.dto.LoginRequest;
import com.dadadarbar.web.dto.LoginResponse;
import com.dadadarbar.web.entity.Admin;
import com.dadadarbar.web.exception.ResourceNotFoundException;
import com.dadadarbar.web.repository.AdminRepository;
import com.dadadarbar.web.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdminRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) throws InvalidCredentialsException {

        Admin admin = repository
                .findByEmail(String.valueOf(request.getEmail()))
                .orElseThrow(() ->
                        new InvalidCredentialsException(
                                "Invalid credentials"
                        ));

        boolean matches =
                encoder.matches(
                        request.getPassword(),
                        admin.getPassword()
                );

        if (!matches) {
            throw new InvalidCredentialsException(
                    "Invalid credentials"
            );
        }

        String token =
                jwtService.generateToken(
                        String.valueOf(admin.getEmail())
                );

        return LoginResponse.builder()
                .token(token)
                .build();
    }
}
