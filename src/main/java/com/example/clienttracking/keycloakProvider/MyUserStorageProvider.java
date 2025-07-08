package com.example.clienttracking.keycloakProvider;

import com.example.clienttracking.model.keycloak;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.models.*;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.user.UserLookupProvider;
import org.keycloak.storage.user.UserQueryProvider;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class MyUserStorageProvider implements
        UserStorageProvider,
        UserLookupProvider,
        CredentialInputValidator,
        UserQueryProvider {

    private final KeycloakSession session;
    private final ComponentModel model;
    private final EntityManager em;

    public MyUserStorageProvider(KeycloakSession session, ComponentModel model, EntityManager em) {
        this.session = session;
        this.model = model;
        this.em = em;
    }

    @Override
    public UserModel getUserById(RealmModel realm, String id) {
        String username = StorageId.externalId(id);
        return getUserByUsername(realm, username);
    }

    @Override
    public UserModel getUserByUsername(RealmModel realm, String username) {
        TypedQuery<keycloak> query = em.createNamedQuery("findUserByUsername", keycloak.class);
        query.setParameter("username", username);
        List<keycloak> result = query.getResultList();
        if (result.isEmpty()) return null;
        return new MyUserAdapter(session, realm, model, result.get(0));
    }

    @Override
    public UserModel getUserByEmail(RealmModel realm, String email) {
        return null;
    }

    @Override
    public boolean supportsCredentialType(String credentialType) {
        return PasswordCredentialModel.TYPE.equals(credentialType);
    }

    @Override
    public boolean isConfiguredFor(RealmModel realm, UserModel user, String credentialType) {
        return supportsCredentialType(credentialType);
    }

    @Override
    public boolean isValid(RealmModel realm, UserModel userModel, CredentialInput input) {
        if (!supportsCredentialType(input.getType())) return false;
        TypedQuery<keycloak> query = em.createNamedQuery("findUserByUsername", keycloak.class);
        query.setParameter("username", userModel.getUsername());
        List<keycloak> result = query.getResultList();
        return !result.isEmpty() && result.get(0).getPassword().equals(input.getChallengeResponse());
    }

    @Override
    public Stream<UserModel> searchForUserStream(RealmModel realm, String search, Integer firstResult, Integer maxResults) {
        TypedQuery<keycloak> query = em.createNamedQuery("findAllUsers", keycloak.class);
        if (firstResult != null) query.setFirstResult(firstResult);
        if (maxResults != null) query.setMaxResults(maxResults);

        return query.getResultList().stream()
                .map(user -> new MyUserAdapter(session, realm, model, user));
    }

    @Override
    public Stream<UserModel> searchForUserStream(RealmModel realm, Map<String, String> params, Integer firstResult, Integer maxResults) {
        return searchForUserStream(realm, "", firstResult, maxResults);
    }

    @Override
    public Stream<UserModel> getGroupMembersStream(RealmModel realmModel, GroupModel groupModel, Integer first, Integer max) {
        return Stream.empty();
    }

    @Override
    public Stream<UserModel> searchForUserByUserAttributeStream(RealmModel realmModel, String attrName, String attrValue) {
        return Stream.empty();
    }

    @Override
    public void close() {
        // No cleanup required
    }
}
