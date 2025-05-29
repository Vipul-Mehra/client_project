package com.example.clienttracking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "keycloak_products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class keycloak {
    @Id
    private String productId;

    private String userId;
}