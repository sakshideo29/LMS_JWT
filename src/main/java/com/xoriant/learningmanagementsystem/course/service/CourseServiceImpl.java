package com.xoriant.learningmanagementsystem.course.service;

import com.xoriant.learningmanagementsystem.course.dto.CourseRequestDto;
import com.xoriant.learningmanagementsystem.course.entity.Course;
import com.xoriant.learningmanagementsystem.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course createCourse(CourseRequestDto courseRequest) {
        if (courseRequest == null || courseRequest.getName() == null || courseRequest.getName().isEmpty()) {
            throw new IllegalArgumentException("Invalid course data: " + courseRequest);
        }
        Course course = new Course();
        course.setName(courseRequest.getName());
        course.setDescription(courseRequest.getDescription());
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + id));
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course updateCourse(Long id, CourseRequestDto courseRequest) {
        Course course = getCourseById(id);
        course.setName(courseRequest.getName());
        course.setDescription(courseRequest.getDescription());
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public void enrollStudent(Long courseId, Long studentId) {
        // Enroll logic goes here
    }
}
