package com.example.clienttracking.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "findUserByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
        @NamedQuery(name = "findAllUsers", query = "SELECT u FROM User u")
})
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
}
