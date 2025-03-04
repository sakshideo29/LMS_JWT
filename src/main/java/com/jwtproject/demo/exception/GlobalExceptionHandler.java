package com.jwtproject.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle specific CustomException
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException ex) {
        // Return the custom exception message with HTTP status BAD_REQUEST (400)
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);  
    }

    // Handle general exception (for other exceptions not specifically handled)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        // Return a generic error message with HTTP status INTERNAL_SERVER_ERROR (500)
        return new ResponseEntity<>("An error occurred. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);  
    }
}
