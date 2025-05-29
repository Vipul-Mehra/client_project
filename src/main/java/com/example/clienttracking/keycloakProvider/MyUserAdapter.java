package com.example.clienttracking.keycloakProvider;



import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialModel;
import org.keycloak.models.*;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.adapter.AbstractUserAdapter;

import java.util.List;
import java.util.stream.Stream;

public class MyUserAdapter extends AbstractUserAdapter {

    private final String username;
    private final ComponentModel model;

    public MyUserAdapter(KeycloakSession session, RealmModel realm, ComponentModel model, UserEntity userEntity) {
        super(session, realm, model);
        this.username = userEntity.getUsername();
        this.model = model;
        setUsername(this.username);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getId() {
        return StorageId.keycloakId(model, username);
    }

    // Required implementation for newer Keycloak versions (22+)
    @Override
    public SubjectCredentialManager credentialManager() {
        return new SubjectCredentialManager() {
            @Override
            public boolean isConfiguredFor(String credentialType) {
                return false;
            }

            @Override
            public boolean isConfiguredLocally(String s) {
                return false;
            }



            @Override
            public boolean isValid(List<CredentialInput> list) {
                return false;
            }

            @Override
            public boolean updateCredential(CredentialInput input) {
                throw new UnsupportedOperationException("Read-only user");
            }

            @Override
            public void updateStoredCredential(CredentialModel credentialModel) {

            }

            @Override
            public CredentialModel createStoredCredential(CredentialModel credentialModel) {
                return null;
            }

            @Override
            public boolean removeStoredCredentialById(String s) {
                return false;
            }

            @Override
            public CredentialModel getStoredCredentialById(String s) {
                return null;
            }

            @Override
            public Stream<CredentialModel> getStoredCredentialsStream() {
                return Stream.empty();
            }

            @Override
            public Stream<CredentialModel> getStoredCredentialsByTypeStream(String s) {
                return Stream.empty();
            }

            @Override
            public CredentialModel getStoredCredentialByNameAndType(String s, String s1) {
                return null;
            }

            @Override
            public boolean moveStoredCredentialTo(String s, String s1) {
                return false;
            }

            @Override
            public void updateCredentialLabel(String s, String s1) {

            }

            @Override
            public void disableCredentialType(String credentialType) {
                throw new UnsupportedOperationException("Read-only user");
            }

            @Override
            public Stream<String> getDisableableCredentialTypesStream() {
                return Stream.empty();
            }

            @Override
            public Stream<String> getConfiguredUserStorageCredentialTypesStream() {
                return Stream.empty();
            }

            @Override
            public CredentialModel createCredentialThroughProvider(CredentialModel credentialModel) {
                return null;
            }
        };
    }
}
