package com.xoriant.learningmanagementsystem.user.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);  // Pass the message to the parent constructor
    }
}
