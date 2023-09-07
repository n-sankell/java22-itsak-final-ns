package com.example.demo.dto;

import java.util.List;

public record PrivateStudentDTO(String name, String gender, String personalIdentityNumber, String phoneNumber, List<AllergyDTO> allergies, List<ActivityDTO> activities) {
}
