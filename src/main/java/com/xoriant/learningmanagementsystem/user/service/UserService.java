package com.xoriant.learningmanagementsystem.user.service;

import com.xoriant.learningmanagementsystem.user.entity.Role;
import com.xoriant.learningmanagementsystem.user.entity.User;

import java.util.List;

/**
 * Service interface for handling user-related operations.
 *
 * <p>This interface defines methods for creating, updating, retrieving, deleting,
 * and managing users in the system. It also provides a method for handling bulk user creation
 * and retrieving users by their roles.</p>
 */
public interface UserService {

    /**
     * Creates a new user in the system.
     *
     * @param user the user to be created
     * @return the created User
     */
    User createUser(User user);

    /**
     * Updates an existing user by their ID.
     *
     * @param id the ID of the user to update
     * @param userDetails the new details to update the user with
     * @return the updated User
     */
    User updateUser(Long id, User userDetails);

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return the User with the specified ID
     */
    User getUserById(Long id);

    /**
     * Deletes a user from the system by their ID.
     *
     * @param id the ID of the user to delete
     */
    void deleteUser(Long id);

    /**
     * Retrieves all users in the system.
     *
     * @return a list of all users
     */
    List<User> getAllUsers();

    /**
     * Handles the bulk creation of users.
     *
     * @param users the list of users to be created
     * @return a list of the created users
     */
    List<User> createUsers(List<User> users);

    /**
     * Retrieves all users with a specific role.
     *
     * @param role the role to filter users by
     * @return a list of users with the specified role
     */
    List<User> getUsersByRole(Role role);
}
