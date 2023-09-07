package com.example.demo.service;

import com.example.demo.dto.AllergyDTO;
import com.example.demo.dto.DTOConverter;
import com.example.demo.repository.AllergyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AllergyService {

    private final AllergyRepository allergyRepository;

    public List<AllergyDTO> getAllAllergies() {
        return allergyRepository.findAll().stream().map(DTOConverter::convert).toList();
    }

}
