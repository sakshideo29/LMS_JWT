package com.xoriant.learningmanagementsystem.enrollment.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.learningmanagementsystem.course.entity.Course;
import com.xoriant.learningmanagementsystem.course.repository.CourseRepository;
import com.xoriant.learningmanagementsystem.enrollment.entity.Enrollment;
import com.xoriant.learningmanagementsystem.enrollment.repository.EnrollmentRepository;
import com.xoriant.learningmanagementsystem.user.entity.User;
import com.xoriant.learningmanagementsystem.user.repository.UserRepository;

import java.util.List;

@Service
public class EnrollmentService implements EnrollmentServiceInterface {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Enrollment enrollStudent(Long studentId, Long courseId) {
        User student = userRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        return enrollmentRepository.save(enrollment);
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourseId(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }
}
