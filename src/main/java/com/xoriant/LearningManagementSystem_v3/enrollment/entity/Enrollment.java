package com.xoriant.LearningManagementSystem_v3.enrollment.entity;


import com.xoriant.LearningManagementSystem_v3.course.entity.Course;
import com.xoriant.LearningManagementSystem_v3.user.entity.User;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
