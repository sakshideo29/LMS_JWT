package com.xoriant.learningmanagementsystem.user.entity;

public enum Role {

    STUDENT("Student User - Access to courses"),
    INSTRUCTOR("Instructor User - Create and manage courses");

    private final String description;

    // Constructor to initialize the description
    Role(String description) {
        this.description = description;
    }

    // Getter for the description
    public String getDescription() {
        return description;
    }

    // Optionally, you can create a method to find Role by description or name
    public static Role fromString(String role) {
        for (Role r : Role.values()) {
            if (r.name().equalsIgnoreCase(role)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Unknown role: " + role);
    }
}
