package com.xoriant.learningmanagementsystem.user.controller;

import com.xoriant.learningmanagementsystem.user.entity.Role;
import com.xoriant.learningmanagementsystem.user.entity.User;
import com.xoriant.learningmanagementsystem.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

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
            // PMD suggestion: validate input and return appropriate response
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
            return ResponseEntity.badRequest().body(null); // PMD suggestion: null checks for better validation
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
            return ResponseEntity.badRequest().body(null); // PMD suggestion: handle edge cases
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
            // PMD suggestion: detailed logging or proper exception handling for invalid role
            return ResponseEntity.badRequest().body(null);
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
            return ResponseEntity.badRequest().build(); // PMD suggestion: handle invalid inputs
        }
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
