package com.xoriant.learningmanagementsystem.course.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

/**
 * Data transfer object for creating or updating a course.
 * Contains necessary information like course name, description, instructor ID, and student IDs.
 */
@Data
public class CourseRequestDto {

    /**
     * Name of the course. Must not be null and should have at least 3 characters.
     */
    @NotNull(message = "Course name cannot be null")
    @Size(min = 3, message = "Course name must be at least 3 characters long")
    private String name;

    /**
     * Description of the course. Must not be null and should have at least 10 characters.
     */
    @NotNull(message = "Course description cannot be null")
    @Size(min = 10, message = "Course description must be at least 10 characters long")
    private String description;

    /**
     * ID of the instructor for the course. Must not be null.
     */
    @NotNull(message = "Instructor ID cannot be null")
    private Long instructorId;

    /**
     * List of student IDs to be enrolled in the course.
     * Can be empty, but should not contain duplicates (consider using a Set if necessary).
     */
    private List<Long> studentIds;
}
