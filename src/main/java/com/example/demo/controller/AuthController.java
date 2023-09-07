package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

import static org.springframework.boot.context.properties.source.ConfigurationPropertyName.isValid;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/public/login")
    public ResponseEntity<Map<String, Boolean>> login(@Valid @RequestBody LoginRequest loginRequest) {
        if (!isValid(loginRequest.username()) || !isValid(loginRequest.password())) {
            throw new ValidationException("No special characters allowed. ");
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        var response = Map.of("loginSuccess", authentication.isAuthenticated());
        return ResponseEntity.ok(response);
    }
}