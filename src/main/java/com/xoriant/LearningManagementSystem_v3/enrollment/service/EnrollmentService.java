package com.xoriant.LearningManagementSystem_v3.enrollment.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.LearningManagementSystem_v3.course.entity.Course;
import com.xoriant.LearningManagementSystem_v3.course.repository.CourseRepository;
import com.xoriant.LearningManagementSystem_v3.enrollment.entity.Enrollment;
import com.xoriant.LearningManagementSystem_v3.enrollment.repository.EnrollmentRepository;
import com.xoriant.LearningManagementSystem_v3.user.entity.User;
import com.xoriant.LearningManagementSystem_v3.user.repository.UserRepository;

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
