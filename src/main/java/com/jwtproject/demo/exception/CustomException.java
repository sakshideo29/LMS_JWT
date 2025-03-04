package com.jwtproject.demo.exception;



public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message); // Pass the message to the RuntimeException constructor
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause); // Pass both message and cause
    }
}

