package com.example.clienttracking.keycloakProvider;

import org.keycloak.Config;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.provider.ProviderConfigurationBuilder;
import org.keycloak.storage.UserStorageProviderFactory;

import java.util.List;

public class MyUserStorageProviderFactory implements UserStorageProviderFactory<MyUserStorageProvider> {

    @Override
    public MyUserStorageProvider create(KeycloakSession session, ComponentModel model) {
        return new MyUserStorageProvider(session, model);
    }

    @Override
    public void init(Config.Scope config) {
        // Global init if needed
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
        // Post init logic
    }

    @Override
    public String getId() {
        return "custom-sql-user-provider";
    }

    @Override
    public void close() {
        // Cleanup
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return ProviderConfigurationBuilder.create()
                .property("myCustomSetting", "My Custom Setting", "Example setting", ProviderConfigProperty.STRING_TYPE, "default_value", null)
                .build();
    }
}
