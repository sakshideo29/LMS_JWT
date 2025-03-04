package com.xoriant.LearningManagementSystem_v3.user.controller;

import com.xoriant.LearningManagementSystem_v3.user.entity.Role;
import com.xoriant.LearningManagementSystem_v3.user.entity.User;
import com.xoriant.LearningManagementSystem_v3.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Modify existing POST mapping to handle both single and multiple users
    @PostMapping("/signup")
    public ResponseEntity<List<User>> createUsers(@RequestBody List<User> users) {
        return ResponseEntity.ok(userService.createUsers(users));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return ResponseEntity.ok(userService.updateUser(id, userDetails));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable String role) {
        try {
            Role userRole = Role.valueOf(role.toUpperCase());  // Convert string to Role enum
            List<User> users = userService.getUsersByRole(userRole);
            return ResponseEntity.ok(users);
        } catch (IllegalArgumentException ex) {
            // If the role is not valid, return a bad request response
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
