package com.xoriant.learningmanagementsystem.course.service;

import com.xoriant.learningmanagementsystem.course.dto.CourseRequestDto;
import com.xoriant.learningmanagementsystem.course.entity.Course;

import java.util.List;

public interface CourseService {
    Course createCourse(CourseRequestDto courseRequest);
    Course getCourseById(Long id);
    List<Course> getAllCourses();
    Course updateCourse(Long id, CourseRequestDto courseRequest);
    void deleteCourse(Long id);
    void enrollStudent(Long courseId, Long studentId);
}
