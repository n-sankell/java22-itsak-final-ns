package com.example.demo.controller;

import com.example.demo.dto.PublicStudentDTO;
import com.example.demo.exception.ValidationException;
import com.example.demo.service.StudentService;
import com.example.demo.validator.AlphaNumeric;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.boot.context.properties.source.ConfigurationPropertyName.isValid;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<PublicStudentDTO>> listStudents() {
        return ResponseEntity.ok(studentService.listStudents());
    }

    // Test Case 1: Data Access - Student
    @GetMapping("/private/student")
    public ResponseEntity<?> getStudentByUsername(@RequestParam @AlphaNumeric String username) {
        if (!isValid(username)) {
            throw new ValidationException("No special characters allowed. ", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(studentService.getStudentByStudentUsername(username));
    }

    // Test Case 3: Search - Student
    @GetMapping("/search/student")
    public ResponseEntity<?> searchStudent(@RequestParam @AlphaNumeric String name) {
        if (!isValid(name)) {
            throw new ValidationException("No special characters allowed. ", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(studentService.searchStudent(name));
    }

}
