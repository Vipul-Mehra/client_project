package com.example.clienttracking.service.impl;

import com.example.clienttracking.dto.keycloakDTO;
import com.example.clienttracking.model.keycloak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.clienttracking.service.keycloakServices;
import com.example.clienttracking.repository.keycloakRepository;
import com.example.clienttracking.repository.TimeSheetsRepository;

@Service
public class keycloakServiceImpl implements keycloakServices {

    @Autowired
    private keycloakRepository keycloakRepository;

    @Override
    public boolean userOwnsProduct(keycloakDTO dto)  {
        return keycloakRepository.existsByUserIdAndProductId(dto.getUserId(), dto.getProductId());
    }
    @Override
    public void addProduct(keycloakDTO dto) {
        keycloak entity = new keycloak(dto.getProductId(), dto.getUserId());
        keycloakRepository.save(entity);
    }
}
