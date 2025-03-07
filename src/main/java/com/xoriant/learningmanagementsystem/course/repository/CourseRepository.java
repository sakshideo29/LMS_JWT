package com.xoriant.learningmanagementsystem.course.repository;

import com.xoriant.learningmanagementsystem.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing Course entities.
 * This interface extends JpaRepository, providing CRUD operations for Course.
 */
public interface CourseRepository extends JpaRepository<Course, Long> {

    /**
     * Finds all courses associated with a given instructor.
     *
     * @param instructorId The ID of the instructor.
     * @return A list of courses associated with the instructor.
     */
    List<Course> findByInstructorId(Long instructorId);
}
