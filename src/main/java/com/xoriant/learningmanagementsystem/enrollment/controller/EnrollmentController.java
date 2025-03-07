package com.xoriant.learningmanagementsystem.enrollment.controller;

import com.xoriant.learningmanagementsystem.enrollment.entity.Enrollment;
import com.xoriant.learningmanagementsystem.enrollment.service.EnrollmentService;
import com.xoriant.learningmanagementsystem.common.exception.StudentNotFoundException;
import com.xoriant.learningmanagementsystem.common.exception.CourseNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/enroll")
    public ResponseEntity<String> enrollStudent(@RequestParam Long studentId, @RequestParam Long courseId) {
        try {
            enrollmentService.enrollStudent(studentId, courseId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Student enrolled successfully.");
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Student not found");
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Course not found");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Invalid data or user role");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred.");
        }
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByStudent(@PathVariable Long studentId, Pageable pageable) {
        try {
            List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudentId(studentId, pageable);
            return ResponseEntity.ok(enrollments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // In case of invalid input
        }
    }

    @GetMapping("/course/{courseName}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByCourse(@PathVariable String courseName, Pageable pageable) {
        try {
            List<Enrollment> enrollments = enrollmentService.getEnrollmentsByCourseName(courseName, pageable);
            return ResponseEntity.ok(enrollments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // In case of invalid input
        }
    }

    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByInstructor(@PathVariable Long instructorId, Pageable pageable) {
        try {
            List<Enrollment> enrollments = enrollmentService.getEnrollmentsByInstructorId(instructorId, pageable);
            return ResponseEntity.ok(enrollments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // In case of invalid input
        }
    }
}
