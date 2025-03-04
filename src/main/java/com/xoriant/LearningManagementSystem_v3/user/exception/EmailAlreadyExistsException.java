package com.xoriant.LearningManagementSystem_v3.user.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);  // Pass the message to the parent constructor
    }
}
