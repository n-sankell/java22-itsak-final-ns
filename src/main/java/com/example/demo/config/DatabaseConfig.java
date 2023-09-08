package com.example.demo.config;

import com.example.demo.data.*;
import com.example.demo.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
@EnableWebSecurity
public class DatabaseConfig {

    @Value("${ADMIN_PASS}")
    private String adminPass;
    @Value("${S1_PASS}")
    private String s1pass;
    @Value("${S2_PASS}")
    private String s2pass;
    @Value("${S3_PASS}")
    private String s3pass;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            StudentRepository studentRepository,
            CourseRepository courseRepository,
            GradesRepository gradesRepository,
            ActivityRepository activityRepository,
            AllergyRepository allergyRepository,
            RoleRepository roleRepository) {

        return args -> {

            Role studentRole = new Role(UUID.randomUUID(), "Student");
            Role adminRole = new Role(UUID.randomUUID(), "Admin");
            roleRepository.saveAll(List.of(studentRole, adminRole));

            Activity activity1 = new Activity(UUID.randomUUID(),"Chess Club");
            Activity activity2 = new Activity(UUID.randomUUID(),"Debate Club");
            Activity activity3 = new Activity(UUID.randomUUID(),"Robotics Club");
            activityRepository.saveAll(List.of(activity1, activity2, activity3));

            Allergy allergy1 = new Allergy(UUID.randomUUID(), "Lactose");
            allergyRepository.save(allergy1);

            Student adminEntity = new Student(UUID.randomUUID(),"admin","admin","Other","","", passwordEncoder.encode(adminPass), new ArrayList<>(), new ArrayList<>(), List.of(adminRole));
            Student student1 = new Student(UUID.randomUUID(),"anna123","Anna","Female","860101-0101","070-1234567", passwordEncoder.encode(s1pass), new ArrayList<>(), List.of(activity1), List.of(studentRole));
            Student student2 = new Student(UUID.randomUUID(),"john123","John","Male","900202-0202","071-2345678", passwordEncoder.encode(s2pass) ,new ArrayList<>(), List.of(activity2), List.of(studentRole));
            Student student3 = new Student(UUID.randomUUID(),"alex123","Alex","Other", "950303-0303", "072-3456789", passwordEncoder.encode(s3pass), List.of(allergy1), List.of(activity3), List.of(studentRole));
            studentRepository.saveAll(List.of(adminEntity, student1, student2, student3));

            Course course1 = new Course(UUID.randomUUID(),"Mathematics");
            Course course2 = new Course(UUID.randomUUID(),"History");
            Course course3 = new Course(UUID.randomUUID(),"Physics");
            courseRepository.saveAll(List.of(course1, course2, course3));

            Grade grade1 = new Grade(UUID.randomUUID(), student1, course1, 'A');
            Grade grade2 = new Grade(UUID.randomUUID(), student2, course2, 'B');
            Grade grade3 = new Grade(UUID.randomUUID(), student3, course3, 'C');
            gradesRepository.saveAll(List.of(grade1, grade2, grade3));

        };
    }

}
