package com.xoriant.learningmanagementsystem.common.exception;

import com.xoriant.learningmanagementsystem.user.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * GlobalExceptionHandler is responsible for handling exceptions thrown by the system.
 * It catches specific exceptions first and logs the errors.
 *
 * <p>All exceptions are handled gracefully and appropriate HTTP responses with meaningful messages
 * are sent to the client.</p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Logger for logging exception details
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles ResourceNotFoundException and returns a 404 response with a user-friendly message.
     *
     * @param ex the ResourceNotFoundException thrown
     * @return ResponseEntity containing the error message and HTTP status code 404
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        logger.error("Resource not found: {}", ex.getMessage(), ex);
        // Return a user-friendly error message with status 404 (Not Found)
        return new ResponseEntity<>(ErrorMessages.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles BadRequestException (e.g., IllegalArgumentException or IllegalStateException).
     * Returns a 400 response with a user-friendly message.
     *
     * @param ex the Exception thrown (can be IllegalArgumentException, IllegalStateException, etc.)
     * @return ResponseEntity containing the error message and HTTP status code 400
     */
    @ExceptionHandler({ IllegalArgumentException.class, IllegalStateException.class })
    public ResponseEntity<String> handleBadRequestException(Exception ex) {
        logger.error("Bad request: {}", ex.getMessage(), ex);
        // Return a user-friendly error message with status 400 (Bad Request)
        return new ResponseEntity<>("Bad Request: Invalid input", HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles any general Exception. It returns a 500 response with a generic error message.
     *
     * @param ex the Exception thrown
     * @return ResponseEntity containing the error message and HTTP status code 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        logger.error("An error occurred: {}", ex.getMessage(), ex);
        // Return a generic error message with status 500 (Internal Server Error)
        return new ResponseEntity<>(ErrorMessages.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles CourseNotFoundException (i.e., when a course is not found in the database).
     * Returns a 404 response with a user-friendly message.
     *
     * @param ex the RuntimeException thrown
     * @return ResponseEntity containing the error message and HTTP status code 404
     */
    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<String> handleCourseNotFoundException(CourseNotFoundException ex) {
        logger.error("Course not found: {}", ex.getMessage(), ex);
        // Return a user-friendly error message with status 404 (Not Found)
        return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
    }

    /**
     * Handles StudentNotFoundException (i.e., when a student is not found in the database).
     * Returns a 404 response with a user-friendly message.
     *
     * @param ex the RuntimeException thrown
     * @return ResponseEntity containing the error message and HTTP status code 404
     */
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> handleStudentNotFoundException(StudentNotFoundException ex) {
        logger.error("Student not found: {}", ex.getMessage(), ex);
        // Return a user-friendly error message with status 404 (Not Found)
        return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
    }
}
