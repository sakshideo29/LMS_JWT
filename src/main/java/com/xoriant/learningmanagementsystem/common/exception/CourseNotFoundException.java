package com.xoriant.learningmanagementsystem.common.exception;


/**
 * Custom exception to be thrown when a course is not found.
 */
public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(String message) {
        super(message);
    }
}