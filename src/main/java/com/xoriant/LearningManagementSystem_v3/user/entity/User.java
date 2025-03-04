package com.xoriant.LearningManagementSystem_v3.user.entity;




import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Long type id

    private String name;

    private String email;
    

    @Enumerated(EnumType.STRING)
    private Role role;
}
