package com.example.demo.service;

import com.example.demo.data.Student;
import com.example.demo.dto.DTOConverter;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.PrivateStudentDTO;
import com.example.demo.dto.PublicStudentDTO;
import com.example.demo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.example.demo.util.TokenUtil.getAccessToken;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@Service
@AllArgsConstructor
public class StudentService implements UserDetailsService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public List<PublicStudentDTO> listStudents() {
        return studentRepository.findAll().stream().map(DTOConverter::convertPublic).toList();
    }

    public List<PublicStudentDTO> searchStudent(@RequestParam String name) {
        return studentRepository.findStudentByNameIgnoreCase(name).stream().map(DTOConverter::convertPublic).toList();
    }

    public PrivateStudentDTO getStudentByStudentUsername(String username) {
        return DTOConverter.convertPrivate(studentRepository.findStudentByUserName(username));
    }

    public ResponseEntity<?> userLogin(LoginRequest loginRequest) {
        System.out.println(loginRequest.username());
        if (studentRepository.existsByUserName(loginRequest.username())) {
            Student student = studentRepository.findStudentByUserName(loginRequest.username());
            System.out.println(student.getName());

            if (passwordEncoder.matches(student.getPassword(), loginRequest.password())) {
                String token = getAccessToken(student, System.currentTimeMillis());

                return status(OK).body(token);
            }
        }
        return status(FORBIDDEN).body("Login was unsuccessful");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findStudentByUserName(username);
        if (student == null) {
            throw new UsernameNotFoundException("Student was not found");
        } else {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            student.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole())));
            return new org.springframework.security.core.userdetails.User(student.getUserName(), student.getPassword(), authorities);
        }
    }

}
