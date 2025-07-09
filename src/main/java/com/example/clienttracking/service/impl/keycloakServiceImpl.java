// src/main/java/com/example/clienttracking/service/impl/keycloakServiceImpl.java
package com.example.clienttracking.service.impl;

import com.example.clienttracking.dto.keycloakDTO;
import com.example.clienttracking.model.ProductOwnership; // Use the ProductOwnership entity
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.clienttracking.service.keycloakServices;
// Remove unused imports like UserRepository if it's not needed here
// import com.example.clienttracking.repository.UserRepository;
import com.example.clienttracking.repository.ProductOwnershipRepository;
import com.example.clienttracking.repository.TimeSheetsRepository;

@Service
public class keycloakServiceImpl implements keycloakServices {

    // Inject the ProductOwnershipRepository
    @Autowired
    private ProductOwnershipRepository productOwnershipRepository;

    // If this is truly not used, remove it and its import
    @Autowired(required = false)
    private TimeSheetsRepository timeSheetsRepository;

    @Override
    public boolean userOwnsProduct(keycloakDTO dto)  {
        return productOwnershipRepository.existsByUserIdAndProductId(dto.getUserId(), dto.getProductId());
    }

    @Override
    public void addProduct(keycloakDTO dto) {
        ProductOwnership entity = new ProductOwnership(dto.getProductId(), dto.getUserId());
        productOwnershipRepository.save(entity);
    }
}