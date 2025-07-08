// src/main/java/com/example/clienttracking/model/ProductOwnership.java
package com.example.clienttracking.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_ownership") // Clear table name for this specific data
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOwnership { // This entity maps product IDs to Keycloak user IDs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productId;

    @Column(nullable = false)
    private String userId; // This stores the Keycloak 'sub' ID

    public ProductOwnership(String productId, String userId) {
        this.productId = productId;
        this.userId = userId;
    }
}