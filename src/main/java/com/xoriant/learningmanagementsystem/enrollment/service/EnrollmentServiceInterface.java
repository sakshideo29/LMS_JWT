package com.xoriant.learningmanagementsystem.enrollment.service;

import com.xoriant.learningmanagementsystem.enrollment.entity.Enrollment;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service interface for managing enrollments in courses.
 */
public interface EnrollmentServiceInterface {

    /**
     * Enrolls a student in a course.
     *
     * @param studentId The ID of the student.
     * @param courseId  The ID of the course.
     * @return The created enrollment.
     */
    Enrollment enrollStudent(Long studentId, Long courseId);

    /**
     * Retrieves all enrollments by student ID with pagination.
     *
     * @param studentId The ID of the student.
     * @param pageable  The pagination information.
     * @return A paginated list of enrollments for the student.
     */
    List<Enrollment> getEnrollmentsByStudentId(Long studentId, Pageable pageable);

    /**
     * Retrieves all enrollments by course name with pagination.
     *
     * @param courseName The name of the course.
     * @param pageable   The pagination information.
     * @return A paginated list of enrollments for the course.
     */
    List<Enrollment> getEnrollmentsByCourseName(String courseName, Pageable pageable);

    /**
     * Gets the count of enrollments for a student.
     *
     * @param studentId The ID of the student.
     * @return The number of enrollments for the student.
     */
    Long getEnrollmentCountByStudentId(Long studentId);

    /**
     * Gets the count of enrollments for a course.
     *
     * @param courseName The name of the course.
     * @return The number of enrollments for the course.
     */
    Long getEnrollmentCountByCourseName(String courseName);

    /**
     * Retrieves all enrollments by instructor ID with pagination.
     *
     * @param instructorId The ID of the instructor.
     * @param pageable     The pagination information.
     * @return A paginated list of enrollments for the instructor's courses.
     */
    List<Enrollment> getEnrollmentsByInstructorId(Long instructorId, Pageable pageable);
}
