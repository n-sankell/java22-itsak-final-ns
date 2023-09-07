package com.example.demo.controller;

import com.example.demo.dto.AllergyDTO;
import com.example.demo.service.AllergyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AllergyController {

    private final AllergyService allergyService;

    // Test Case: List all students with health data
    @GetMapping("/private/allergies")
    public ResponseEntity<List<AllergyDTO>> getAllAllergies() {
        return ResponseEntity.ok(allergyService.getAllAllergies());
    }

}
