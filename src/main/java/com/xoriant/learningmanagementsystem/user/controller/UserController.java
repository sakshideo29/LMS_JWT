package com.xoriant.learningmanagementsystem.user.controller;

import com.xoriant.learningmanagementsystem.user.entity.Role;
import com.xoriant.learningmanagementsystem.user.entity.User;
import com.xoriant.learningmanagementsystem.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // Constructor injection to avoid field injection
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Create multiple users at once.
     *
     * @param users List of users to be created.
     * @return ResponseEntity with the created users.
     */
    @PostMapping("/signup")
    public ResponseEntity<List<User>> createUsers(@RequestBody List<User> users) {
        if (users == null || users.isEmpty()) {
            logger.warn("Attempt to create users with empty list.");
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(userService.createUsers(users));
    }

    /**
     * Update the details of a user.
     *
     * @param id           ID of the user to be updated.
     * @param userDetails  New user details.
     * @return ResponseEntity with updated user.
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        if (id == null || userDetails == null) {
            logger.warn("Attempt to update user with invalid ID or userDetails: id={} userDetails={}", id, userDetails);
            return ResponseEntity.badRequest().body(null); // Improved validation
        }
        return ResponseEntity.ok(userService.updateUser(id, userDetails));
    }

    /**
     * Fetch user details by their ID.
     *
     * @param id ID of the user.
     * @return ResponseEntity with user data.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        if (id == null) {
            logger.warn("Attempt to fetch user with invalid ID: {}", id);
            return ResponseEntity.badRequest().body(null); // Improved validation
        }
        return ResponseEntity.ok(userService.getUserById(id));
    }

    /**
     * Fetch all users in the system.
     *
     * @return ResponseEntity with list of users.
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users == null || users.isEmpty()) {
            return ResponseEntity.noContent().build(); // PMD suggestion: handle empty results gracefully
        }
        return ResponseEntity.ok(users);
    }

    /**
     * Get users by their role.
     *
     * @param role Role name to fetch users for.
     * @return ResponseEntity with list of users by role.
     */
    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable String role) {
        try {
            Role userRole = Role.valueOf(role.toUpperCase()); // PMD suggestion: validate role conversion
            List<User> users = userService.getUsersByRole(userRole);
            if (users == null || users.isEmpty()) {
                return ResponseEntity.noContent().build(); // PMD suggestion: gracefully handle no data
            }
            return ResponseEntity.ok(users);
        } catch (IllegalArgumentException ex) {
            logger.error("Invalid role provided: {}", role, ex);
            return ResponseEntity.badRequest().body(null); // PMD suggestion: detailed logging or proper exception handling for invalid role
        }
    }

    /**
     * Delete a user by their ID.
     *
     * @param id ID of the user to be deleted.
     * @return ResponseEntity with no content status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (id == null) {
            logger.warn("Attempt to delete user with invalid ID: {}", id);
            return ResponseEntity.badRequest().build(); // PMD suggestion: handle invalid inputs
        }
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
