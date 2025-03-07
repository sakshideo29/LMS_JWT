package com.xoriant.learningmanagementsystem.user.repository;

import com.xoriant.learningmanagementsystem.user.entity.Role;
import com.xoriant.learningmanagementsystem.user.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for accessing User entities from the database.
 *
 * <p>This interface extends JpaRepository, which provides basic CRUD operations
 * for the User entity, as well as custom queries like finding users by their role
 * or checking if an email already exists.</p>
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Checks if a User with the given email already exists in the system.
     *
     * @param email the email address to check
     * @return true if a User with the given email exists, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Finds a User by their unique identifier.
     *
     * @param id the ID of the User to find
     * @return an Optional containing the User if found, or empty if not
     */
    Optional<User> findById(Long id);

    /**
     * Finds all Users with the given role.
     *
     * @param role the role of the Users to find
     * @return a list of Users with the specified role
     */
    List<User> findByRole(Role role);
}
