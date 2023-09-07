package com.example.demo.service;

import com.example.demo.dto.DTOConverter;
import com.example.demo.dto.PrivateStudentDTO;
import com.example.demo.dto.PublicStudentDTO;
import com.example.demo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService implements UserDetailsService {

    private final StudentRepository studentRepository;

    public List<PublicStudentDTO> listStudents() {
        return studentRepository.findAll().stream().map(DTOConverter::convertPublic).toList();
    }

    public List<PublicStudentDTO> searchStudent(@RequestParam String name) {
        return studentRepository.findStudentByNameIgnoreCase(name).stream().map(DTOConverter::convertPublic).toList();
    }

    public PrivateStudentDTO getStudentByStudentUsername(String username) {
        return DTOConverter.convertPrivate(studentRepository.findStudentByUserName(username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

}
