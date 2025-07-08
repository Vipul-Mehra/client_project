// src/main/java/com/example/clienttracking/repository/ProductOwnershipRepository.java
package com.example.clienttracking.repository;

import com.example.clienttracking.model.ProductOwnership; // Manages the ProductOwnership entity
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOwnershipRepository extends JpaRepository<ProductOwnership, Long> {
    // This is where existsByUserIdAndProductId belongs
    boolean existsByUserIdAndProductId(String userId, String productId);
}