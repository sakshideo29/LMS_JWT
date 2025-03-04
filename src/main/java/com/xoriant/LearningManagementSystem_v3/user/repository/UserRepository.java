package com.xoriant.LearningManagementSystem_v3.user.repository;

import com.xoriant.LearningManagementSystem_v3.user.entity.Role;
import com.xoriant.LearningManagementSystem_v3.user.entity.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);  // Method to check if email exists
    Optional<User> findById(Long id);  // Method to find user by ID
    List<User> findByRole(Role role); 
}
