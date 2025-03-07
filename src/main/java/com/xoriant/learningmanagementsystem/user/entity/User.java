package com.xoriant.learningmanagementsystem.user.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a User entity in the system.
 *
 * <p>This entity is mapped to the 'users' table in the database and contains information
 * about a user such as their name, email, and role.</p>
 *
 * <p>The email field is unique to ensure no two users have the same email address.</p>
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    /**
     * The unique identifier for a User.
     * This field is auto-generated using an auto-increment strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the user.
     * This field represents the user's full name.
     */
    private String name;

    /**
     * The email address of the user.
     * This field must be unique across all users.
     */
    private String email;

    /**
     * The role assigned to the user.
     * Role is represented by an enum to differentiate between types of users (e.g., Admin, Student, Instructor).
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * Generates a string representation of the User object.
     * This is useful for logging and debugging purposes.
     *
     * @return a string representing the User object.
     */
    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', email='" + email + "', role=" + role + "}";
    }
}
