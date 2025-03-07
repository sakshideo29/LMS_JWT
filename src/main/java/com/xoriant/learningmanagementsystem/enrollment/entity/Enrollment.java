package com.xoriant.learningmanagementsystem.enrollment.entity;

import com.xoriant.learningmanagementsystem.course.entity.Course;
import com.xoriant.learningmanagementsystem.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)  // Ensuring student cannot be null
    private User student;

    @Getter
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)  // Ensuring course cannot be null
    private Course course;

    /**
     * Lombok @Data generates getters and setters for this field.
     */

    // Optionally, if you need a specific behavior, you could add custom setter logic like below
    public void setCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }
        this.course = course;
    }


}
