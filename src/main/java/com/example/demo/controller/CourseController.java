package com.example.demo.controller;

import com.example.demo.dto.CourseDTO;
import com.example.demo.exception.ValidationException;
import com.example.demo.service.CourseService;
import com.example.demo.validator.AlphaNumeric;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.boot.context.properties.source.ConfigurationPropertyName.isValid;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;

    // Test Case 2: Show Course List
    @GetMapping("/public/courses")
    public ResponseEntity<List<CourseDTO>> getCourses() {
        return ResponseEntity.ok(courseService.getCourses());
    }

    // Test Case 4: Search - Course
    @GetMapping("/public/search/course")
    public ResponseEntity<List<CourseDTO>> searchCourse(@RequestParam @AlphaNumeric String name) {
        if (!isValid(name)) {
            throw new ValidationException("No special characters allowed. ");
        }
        return ResponseEntity.ok(courseService.searchCourse(name));
    }

}
