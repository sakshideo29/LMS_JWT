package com.xoriant.LearningManagementSystem_v3.user.service;

import com.xoriant.LearningManagementSystem_v3.user.entity.Role;
import com.xoriant.LearningManagementSystem_v3.user.entity.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User updateUser(Long id, User userDetails);

    User getUserById(Long id);

    void deleteUser(Long id);

    List<User> getAllUsers();

    // Method to handle bulk user creation
    List<User> createUsers(List<User> users);
    
    List<User> getUsersByRole(Role role); 
}
