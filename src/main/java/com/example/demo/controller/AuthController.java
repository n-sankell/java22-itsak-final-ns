package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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

    private final StudentService studentService;

    public AuthController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/public/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        if (!isValid(loginRequest.username()) || !isValid(loginRequest.password())) {
            throw new ValidationException("No special characters allowed. ", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(studentService.userLogin(loginRequest));
    }

}