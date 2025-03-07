package com.xoriant.learningmanagementsystem.user.entity;

/**
 * Enum representing different user roles in the system.
 * Each role comes with a description that explains its purpose.
 *
 * <p>Currently, there are two roles: STUDENT and INSTRUCTOR.</p>
 */
public enum Role {

    /**
     * Represents a student user who has access to courses.
     */
    STUDENT("Student User - Access to courses"),

    /**
     * Represents an instructor user who can create and manage courses.
     */
    INSTRUCTOR("Instructor User - Create and manage courses");

    private final String description;

    /**
     * Constructor to initialize the description of the role.
     *
     * @param description the description of the role
     */
    Role(String description) {
        this.description = description;
    }

    /**
     * Gets the description of the role.
     *
     * @return the description of the role
     */
    public String getDescription() {
        return description;
    }

    /**
     * Converts a string to the corresponding Role enum.
     *
     * @param role the string representation of the role
     * @return the corresponding Role enum
     * @throws IllegalArgumentException if no matching role is found
     */
    public static Role fromString(String role) {
        for (Role r : Role.values()) {
            if (r.name().equalsIgnoreCase(role)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Unknown role: " + role);
    }
}
