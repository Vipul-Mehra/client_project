package com.example.clienttracking.keycloakProvider;

import jakarta.persistence.EntityManager;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.storage.UserStorageProviderFactory;

public class MyUserStorageProviderFactory implements UserStorageProviderFactory<MyUserStorageProvider> {


    public MyUserStorageProvider create(KeycloakSession session, ComponentModel model, EntityManager em) {
        return new MyUserStorageProvider(session, model , em);
    }

    @Override
    public void init(org.keycloak.Config.Scope config) {
        // Optional init logic
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
        // Optional post-init logic
    }

    @Override
    public void close() {
        // Optional cleanup
    }

    @Override
    public MyUserStorageProvider create(KeycloakSession keycloakSession, ComponentModel componentModel) {
        return null;
    }

    @Override
    public String getId() {
        return "sql-user-provider";
    }
}
