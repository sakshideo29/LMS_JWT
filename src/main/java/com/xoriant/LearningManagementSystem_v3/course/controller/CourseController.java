package com.xoriant.LearningManagementSystem_v3.course.controller;

import com.xoriant.LearningManagementSystem_v3.course.dto.CourseRequestDto;
import com.xoriant.LearningManagementSystem_v3.course.entity.Course;
import com.xoriant.LearningManagementSystem_v3.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/courses")
    public ResponseEntity<Course> createCourse(@RequestBody CourseRequestDto courseRequest) {
        Course createdCourse = courseService.createCourse(courseRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody CourseRequestDto courseRequest) {
        return ResponseEntity.ok(courseService.updateCourse(id, courseRequest));
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted successfully");
    }

    @PostMapping("/courses/{courseId}/enroll")
    public ResponseEntity<String> enrollStudent(@PathVariable Long courseId, @RequestBody Long studentId) {
        courseService.enrollStudent(courseId, studentId);
        return ResponseEntity.ok("Student enrolled successfully");
    }
}
