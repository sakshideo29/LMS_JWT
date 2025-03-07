package com.xoriant.learningmanagementsystem.user.service;

import com.xoriant.learningmanagementsystem.user.entity.Role;
import com.xoriant.learningmanagementsystem.user.entity.User;
import com.xoriant.learningmanagementsystem.user.exception.ResourceNotFoundException;
import com.xoriant.learningmanagementsystem.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for handling user-related operations.
 *
 * <p>This class provides methods to create, update, delete, and retrieve users,
 * as well as handle bulk user creation. It includes exception handling for common issues
 * like non-existing users and duplicate emails.</p>
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new user.
     *
     * @param user the user to create
     * @return the created user
     * @throws IllegalArgumentException if the email already exists
     */
    @Override
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            logger.error("Attempted to create user with existing email: {}", user.getEmail());
            throw new IllegalArgumentException("Email already exists");
        }
        return userRepository.save(user);
    }

    /**
     * Updates an existing user.
     *
     * @param id the user ID
     * @param userDetails the new user details
     * @return the updated user
     * @throws ResourceNotFoundException if the user is not found
     */
    @Override
    public User updateUser(Long id, User userDetails) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("User not found with id: {}", id);
                    return new ResourceNotFoundException("User not found");
                });
        existingUser.setName(userDetails.getName());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setRole(userDetails.getRole());
        return userRepository.save(existingUser);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the user ID
     * @return the user with the specified ID
     * @throws ResourceNotFoundException if the user is not found
     */
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("User not found with id: {}", id);
                    return new ResourceNotFoundException("User not found");
                });
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the user ID
     * @throws ResourceNotFoundException if the user is not found
     */
    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("User not found with id: {}", id);
                    return new ResourceNotFoundException("User not found");
                });
        userRepository.delete(user);
    }

    /**
     * Retrieves all users in the system.
     *
     * @return a list of all users
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves all users by their role.
     *
     * @param role the role to filter users by
     * @return a list of users with the specified role
     */
    @Override
    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    /**
     * Creates a list of users in bulk.
     *
     * @param users the list of users to create
     * @return a list of created users
     * @throws IllegalArgumentException if one or more users have an existing email
     */
    @Override
    public List<User> createUsers(List<User> users) {
        // Check for any duplicate emails before processing
        List<User> usersWithExistingEmail = users.stream()
                .filter(user -> userRepository.existsByEmail(user.getEmail()))
                .collect(Collectors.toList());

        if (!usersWithExistingEmail.isEmpty()) {
            logger.error("One or more users have an existing email: {}", usersWithExistingEmail);
            throw new IllegalArgumentException("One or more users have an existing email.");
        }

        // Save users if no duplicates found
        return userRepository.saveAll(users);
    }
}
