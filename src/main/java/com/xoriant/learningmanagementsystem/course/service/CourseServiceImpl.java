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
    private final StudentRepository studentRepository;

    // Constructor injection
    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Course createCourse(CourseRequestDto courseRequest) {
        Course course = new Course();
        course.setName(courseRequest.getName());
        course.setDescription(courseRequest.getDescription());
        course.setInstructorId(courseRequest.getInstructorId());
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public Course updateCourse(Long id, CourseRequestDto courseRequest) {
        Course course = getCourseById(id);
        course.setName(courseRequest.getName());
        course.setDescription(courseRequest.getDescription());
        course.setInstructorId(courseRequest.getInstructorId());
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public void enrollStudent(Long courseId, Long studentId) {
        // Logic to enroll student into a course
    }
}
