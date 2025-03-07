package com.xoriant.learningmanagementsystem.course.controller;

import com.xoriant.learningmanagementsystem.course.dto.CourseRequestDto;
import com.xoriant.learningmanagementsystem.course.entity.Course;
import com.xoriant.learningmanagementsystem.course.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Create a new course.
     *
     * @param courseRequest DTO containing course data.
     * @return ResponseEntity with the created course.
     */
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody CourseRequestDto courseRequest) {
        if (courseRequest == null || courseRequest.getName() == null || courseRequest.getName().isEmpty()) {
            logger.warn("Attempt to create course with invalid data: {}", courseRequest);
            return ResponseEntity.badRequest().body(null);
        }
        Course createdCourse = courseService.createCourse(courseRequest);
        return ResponseEntity.ok(createdCourse);
    }

    /**
     * Update an existing course by its ID.
     *
     * @param id             Course ID to be updated.
     * @param courseRequest  DTO containing updated course data.
     * @return ResponseEntity with the updated course.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody CourseRequestDto courseRequest) {
        if (id == null || courseRequest == null || courseRequest.getName() == null || courseRequest.getName().isEmpty()) {
            logger.warn("Attempt to update course with invalid data: id={} courseRequest={}", id, courseRequest);
            return ResponseEntity.badRequest().body(null);
        }
        Course updatedCourse = courseService.updateCourse(id, courseRequest);
        return ResponseEntity.ok(updatedCourse);
    }

    /**
     * Get course details by its ID.
     *
     * @param id Course ID.
     * @return ResponseEntity with the course data.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        if (id == null) {
            logger.warn("Attempt to fetch course with invalid ID: {}", id);
            return ResponseEntity.badRequest().body(null);
        }
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }

    /**
     * Get all courses.
     *
     * @return ResponseEntity with the list of courses.
     */
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        if (courses == null || courses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(courses);
    }

    /**
     * Enroll a student in a course.
     *
     * @param courseId  ID of the course.
     * @param studentId ID of the student.
     * @return ResponseEntity with status of the enrollment.
     */
    @PostMapping("/{courseId}/enroll")
    public ResponseEntity<Void> enrollStudent(@PathVariable Long courseId, @RequestParam Long studentId) {
        if (courseId == null || studentId == null) {
            logger.warn("Attempt to enroll student with invalid courseId={} or studentId={}", courseId, studentId);
            return ResponseEntity.badRequest().build();
        }
        courseService.enrollStudent(courseId, studentId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Delete a course by its ID.
     *
     * @param id Course ID.
     * @return ResponseEntity with no content status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        if (id == null) {
            logger.warn("Attempt to delete course with invalid ID: {}", id);
            return ResponseEntity.badRequest().build();
        }
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
