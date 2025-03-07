package com.xoriant.learningmanagementsystem.user.exception;

/**
 * Custom exception thrown when a user's email already exists in the system.
 *
 * <p>This exception is used when trying to create or update a user with an email that
 * already exists in the database. It extends {@link RuntimeException} and provides
 * a custom error message for better clarity.</p>
 */
public class EmailAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new EmailAlreadyExistsException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link Throwable#getMessage()} method)
     */
    public EmailAlreadyExistsException(String message) {
        super(message);  // Pass the message to the parent constructor
    }
}
