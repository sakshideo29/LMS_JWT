package com.xoriant.learningmanagementsystem.user.exception;

import java.io.Serializable;

/**
 * Custom exception thrown when a resource (e.g., a user) is not found.
 * <p>This exception is thrown when attempting to access a resource that does not exist in the system.
 * It extends {@link RuntimeException} and provides a custom error message for better clarity.</p>
 */
public class ResourceNotFoundException extends RuntimeException implements Serializable {

    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link Throwable#getMessage()} method)
     */
    public ResourceNotFoundException(String message) {
        super(message); // Pass the message to the parent constructor
    }
}
