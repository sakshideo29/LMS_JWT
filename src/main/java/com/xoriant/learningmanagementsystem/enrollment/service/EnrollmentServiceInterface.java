package com.xoriant.learningmanagementsystem.enrollment.service;



import java.util.List;

import com.xoriant.learningmanagementsystem.enrollment.entity.Enrollment;

public interface EnrollmentServiceInterface {
    Enrollment enrollStudent(Long studentId, Long courseId);

    List<Enrollment> getEnrollmentsByStudentId(Long studentId);

    List<Enrollment> getEnrollmentsByCourseId(Long courseId);
}
