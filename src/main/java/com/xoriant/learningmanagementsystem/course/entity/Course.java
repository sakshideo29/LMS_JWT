package com.xoriant.learningmanagementsystem.course.entity;

import com.xoriant.learningmanagementsystem.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import java.util.Objects;

/**
 * Represents a course entity with its details such as name, description, instructor, and students.
 * The class includes JPA annotations to map the entity to the "courses" table in the database.
 * Includes bi-directional relationship with User (instructor and students).
 */
@Entity
@Table(name = "courses")
@Data
public class Course {

    /**
     * The unique identifier for the course.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the course.
     */
    private String name;

    /**
     * The description of the course.
     */
    private String description;

    /**
     * The instructor of the course.
     */
    @ManyToOne
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private User instructor;

    /**
     * The list of students enrolled in the course.
     */
    @ManyToMany
    @JoinTable(
            name = "course_enrollments",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    @JsonManagedReference  // Prevent circular references in JSON serialization
    private List<User> students;

    /**
     * Override equals to ensure comparison is based on the ID (primary key) only.
     * This avoids potential issues when comparing entities that have relationships with many-to-many or one-to-many associations.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Course course = (Course) obj;
        return Objects.equals(id, course.id);
    }

    /**
     * Override hashCode to ensure it's consistent with equals, which is based on the ID (primary key).
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
