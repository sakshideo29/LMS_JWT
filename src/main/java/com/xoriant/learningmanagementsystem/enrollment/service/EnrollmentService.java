package com.xoriant.learningmanagementsystem.enrollment.service;

import com.xoriant.learningmanagementsystem.enrollment.entity.Enrollment;
import com.xoriant.learningmanagementsystem.enrollment.repository.EnrollmentRepository;
import com.xoriant.learningmanagementsystem.course.entity.Course;
import com.xoriant.learningmanagementsystem.course.repository.CourseRepository;
import com.xoriant.learningmanagementsystem.user.entity.User;
import com.xoriant.learningmanagementsystem.user.repository.UserRepository;
import com.xoriant.learningmanagementsystem.common.exception.StudentNotFoundException;
import com.xoriant.learningmanagementsystem.common.exception.CourseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));

        // Check if the user is a student
        if (!student.getRole().equals("STUDENT")) {
            throw new IllegalArgumentException("User must be a student to enroll in a course");
        }

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        return enrollmentRepository.save(enrollment);
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudentId(Long studentId, Pageable pageable) {
        Page<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId, pageable);
        return enrollments.getContent();
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourseName(String courseName, Pageable pageable) {
        Page<Enrollment> enrollments = enrollmentRepository.findByCourse_Name(courseName, pageable);
        return enrollments.getContent();
    }

    @Override
    public Long getEnrollmentCountByStudentId(Long studentId) {
        return enrollmentRepository.countByStudentId(studentId);
    }

    @Override
    public Long getEnrollmentCountByCourseName(String courseName) {
        return enrollmentRepository.countByCourse_Name(courseName);
    }

    @Override
    public List<Enrollment> getEnrollmentsByInstructorId(Long instructorId, Pageable pageable) {
        Page<Enrollment> enrollments = enrollmentRepository.findByCourse_InstructorId(instructorId, pageable);
        return enrollments.getContent();
    }
}
