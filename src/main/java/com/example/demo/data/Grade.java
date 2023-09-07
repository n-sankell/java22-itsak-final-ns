package com.example.demo.data;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public final class Grade {
    @Id
    private UUID gradeId;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Course course;
    private char grade;

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }
}
