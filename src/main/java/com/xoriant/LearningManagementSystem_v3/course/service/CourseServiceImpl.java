package com.xoriant.LearningManagementSystem_v3.course.service;

import com.xoriant.LearningManagementSystem_v3.course.dto.CourseRequestDto;
import com.xoriant.LearningManagementSystem_v3.course.entity.Course;
import com.xoriant.LearningManagementSystem_v3.course.repository.CourseRepository;
import com.xoriant.LearningManagementSystem_v3.user.entity.Role;
import com.xoriant.LearningManagementSystem_v3.user.entity.User;
import com.xoriant.LearningManagementSystem_v3.user.repository.UserRepository;
import com.xoriant.LearningManagementSystem_v3.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Course createCourse(CourseRequestDto courseRequest) {
        // Validate instructor role
        User instructor = userRepository.findById(courseRequest.getInstructorId())
                .orElseThrow(() -> new IllegalArgumentException("Instructor not found"));
        if (instructor.getRole() != Role.INSTRUCTOR) {
            throw new IllegalArgumentException("User is not an instructor");
        }

        // Validate students
        List<User> students = userRepository.findAllById(courseRequest.getStudentIds());

        Course course = new Course();
        course.setName(courseRequest.getName());
        course.setDescription(courseRequest.getDescription());
        course.setInstructor(instructor);
        course.setStudents(students);
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course updateCourse(Long id, CourseRequestDto courseRequest) {
        Course course = getCourseById(id);

        // Validate instructor role
        User instructor = userRepository.findById(courseRequest.getInstructorId())
                .orElseThrow(() -> new IllegalArgumentException("Instructor not found"));
        if (instructor.getRole() != Role.INSTRUCTOR) {
            throw new IllegalArgumentException("User is not an instructor");
        }

        // Validate students
        List<User> students = userRepository.findAllById(courseRequest.getStudentIds());

        course.setName(courseRequest.getName());
        course.setDescription(courseRequest.getDescription());
        course.setInstructor(instructor);
        course.setStudents(students);
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = getCourseById(id);
        courseRepository.delete(course);
    }

    @Override
    public void enrollStudent(Long courseId, Long studentId) {
        Course course = getCourseById(courseId);
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        if (student.getRole() != Role.STUDENT) {
            throw new IllegalArgumentException("User is not a student");
        }

        course.getStudents().add(student);
        courseRepository.save(course);
    }
}
