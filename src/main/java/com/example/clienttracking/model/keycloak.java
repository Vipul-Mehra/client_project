// src/main/java/com/example/clienttracking/model/User.java
package com.example.clienttracking.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "application_users") // Renamed table to avoid confusion with "users"
@NamedQueries({
        @NamedQuery(name = "findUserByUsername", query = "SELECT u FROM keycloak u WHERE u.username = :username"),
        @NamedQuery(name = "findAllUsers", query = "SELECT u FROM keycloak u")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class keycloak { // This is your actual user entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password; // Remember to hash passwords!

    // Add other user-specific fields like email, firstName, lastName etc. if needed
    private String email;
}