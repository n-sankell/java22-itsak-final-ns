package com.example.demo.dto;

import java.util.List;

public record PublicStudentDTO(String name, String gender, List<ActivityDTO> activities) {

}
