package com.xoriant.learningmanagementsystem.user.service;

import com.xoriant.learningmanagementsystem.user.entity.Role;
import com.xoriant.learningmanagementsystem.user.entity.User;
import com.xoriant.learningmanagementsystem.user.exception.ResourceNotFoundException;
import com.xoriant.learningmanagementsystem.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        existingUser.setName(userDetails.getName());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setRole(userDetails.getRole());
        return userRepository.save(existingUser);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    @Override
    public List<User> createUsers(List<User> users) {
        // Check if any user has an existing email
        List<User> usersWithExistingEmail = users.stream()
                .filter(user -> userRepository.existsByEmail(user.getEmail()))
                .collect(Collectors.toList());

        if (!usersWithExistingEmail.isEmpty()) {
            throw new IllegalArgumentException("One or more users have an existing email.");
        }

        // If no duplicates, save all users
        return userRepository.saveAll(users);
    }
}
