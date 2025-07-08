// src/main/java/com/example/clienttracking/service/impl/keycloakServiceImpl.java
package com.example.clienttracking.service.impl;

import com.example.clienttracking.dto.keycloakDTO;
import com.example.clienttracking.model.ProductOwnership; // Use the ProductOwnership entity
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.clienttracking.service.keycloakServices;
// Remove unused imports like UserRepository if it's not needed here
// import com.example.clienttracking.repository.UserRepository;
import com.example.clienttracking.repository.ProductOwnershipRepository; // Import the correct repository
import com.example.clienttracking.repository.TimeSheetsRepository; // Still unused, consider removing

@Service
public class keycloakServiceImpl implements keycloakServices {

    // Inject the ProductOwnershipRepository
    @Autowired
    private ProductOwnershipRepository productOwnershipRepository; // Renamed for clarity

    // If this is truly not used, remove it and its import
    @Autowired(required = false)
    private TimeSheetsRepository timeSheetsRepository;

    @Override
    public boolean userOwnsProduct(keycloakDTO dto)  {
        // Call the method on the correct repository
        return productOwnershipRepository.existsByUserIdAndProductId(dto.getUserId(), dto.getProductId());
    }

    @Override
    public void addProduct(keycloakDTO dto) {
        // Create an instance of ProductOwnership and save it
        ProductOwnership entity = new ProductOwnership(dto.getProductId(), dto.getUserId());
        productOwnershipRepository.save(entity);
    }
}