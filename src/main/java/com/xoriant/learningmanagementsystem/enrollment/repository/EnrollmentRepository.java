package com.xoriant.learningmanagementsystem.enrollment.repository;

import com.xoriant.learningmanagementsystem.enrollment.entity.Enrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // Find enrollments by student ID with pagination
    Page<Enrollment> findByStudentId(Long studentId, Pageable pageable);

    // Find enrollments by course name with pagination
    Page<Enrollment> findByCourse_Name(String courseName, Pageable pageable);

    // Count enrollments by student ID
    Long countByStudentId(Long studentId);

    // Count enrollments by course name
    Long countByCourse_Name(String courseName);

    // Find enrollments by instructor ID (assuming the Course entity has a reference to the instructor)
    Page<Enrollment> findByCourse_InstructorId(Long instructorId, Pageable pageable);
}
