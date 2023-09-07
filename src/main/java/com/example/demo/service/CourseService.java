package com.example.demo.service;

import com.example.demo.dto.CourseDTO;
import com.example.demo.dto.DTOConverter;
import com.example.demo.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<CourseDTO> getCourses() {
        return courseRepository.findAll().stream().map(DTOConverter::convert).toList();
    }

    public List<CourseDTO> searchCourse(String courseName) {
        return courseRepository.findByCourseNameIgnoreCase(courseName).stream().map(DTOConverter::convert).toList();
    }

}
