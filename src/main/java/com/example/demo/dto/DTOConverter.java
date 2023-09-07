package com.example.demo.dto;

import com.example.demo.data.Activity;
import com.example.demo.data.Allergy;
import com.example.demo.data.Course;
import com.example.demo.data.Student;

import java.util.ArrayList;
import java.util.List;

public class DTOConverter {

    public static CourseDTO convert(Course course) {
        return new CourseDTO(course.getCourseName());
    }

    public static AllergyDTO convert(Allergy allergy) {
        return new AllergyDTO(allergy.getType());
    }

    public static ActivityDTO convert(Activity activity) {
        return new ActivityDTO(activity.getActivityName());
    }

    public static PublicStudentDTO convertPublic(Student student) {
        return new PublicStudentDTO(student.getName(), student.getGender(), student.getActivities().stream().map(DTOConverter::convert).toList());
    }

    public static PrivateStudentDTO convertPrivate(Student student) {
        return new PrivateStudentDTO(student.getName(), student.getGender(), student.getPersonalIdentityNumber(), student.getPhoneNumber(),
                student.getAllergies().stream().map(DTOConverter::convert).toList(),
                student.getActivities().stream().map(DTOConverter::convert).toList());
    }

}
