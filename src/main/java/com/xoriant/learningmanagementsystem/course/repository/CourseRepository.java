package com.xoriant.learningmanagementsystem.course.repository;

import com.xoriant.learningmanagementsystem.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findById(Long id);
    List<Course> findByInstructorId(Long instructorId);
}
