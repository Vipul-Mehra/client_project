package com.example.clienttracking.keycloakProvider;

import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.models.*;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.models.UserCredentialModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.user.UserLookupProvider;
import org.keycloak.storage.user.UserQueryProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MyUserStorageProvider implements
        UserStorageProvider,
        UserLookupProvider,
        CredentialInputValidator,
        UserQueryProvider {

    private final KeycloakSession session;
    private final ComponentModel model;

    private static final Map<String, UserEntity> fakeDb = new HashMap<>();

    static {
        fakeDb.put("john", new UserEntity("john", "password123"));
        fakeDb.put("alice", new UserEntity("alice", "password456"));
    }

    public MyUserStorageProvider(KeycloakSession session, ComponentModel model) {
        this.session = session;
        this.model = model;
    }

    @Override
    public UserModel getUserById(RealmModel realm, String id) {
        String externalId = StorageId.externalId(id);
        return getUserByUsername(realm, externalId);
    }

    @Override
    public UserModel getUserByUsername(RealmModel realm, String username) {
        UserEntity userEntity = fakeDb.get(username);
        if (userEntity != null) {
            return new MyUserAdapter(session, realm, model, userEntity);
        }
        return null;
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
        return supportsCredentialType(credentialType) && fakeDb.containsKey(user.getUsername());
    }

    @Override
    public boolean isValid(RealmModel realm, UserModel user, CredentialInput input) {
        if (!supportsCredentialType(input.getType()) || !(input instanceof UserCredentialModel)) {
            return false;
        }

        UserCredentialModel cred = (UserCredentialModel) input;
        UserEntity entity = fakeDb.get(user.getUsername());

        // ⚠️ WARNING: This is plaintext comparison. In production, use hashed passwords.
        return entity != null && entity.getPassword().equals(cred.getChallengeResponse());
    }

    @Override
    public int getUsersCount(RealmModel realm) {
        return fakeDb.size();
    }

    @Override
    public Stream<UserModel> searchForUserStream(RealmModel realm, String search, Integer firstResult, Integer maxResults) {
        return fakeDb.values().stream()
                .filter(userEntity -> userEntity.getUsername().contains(search))
                .skip(firstResult != null ? firstResult : 0)
                .limit(maxResults != null ? maxResults : Integer.MAX_VALUE)
                .map(userEntity -> new MyUserAdapter(session, realm, model, userEntity));
    }

    @Override
    public Stream<UserModel> searchForUserStream(RealmModel realm, Map<String, String> params, Integer firstResult, Integer maxResults) {
        String usernameParam = params.get(UserModel.USERNAME);
        Stream<UserEntity> userStream = fakeDb.values().stream();

        if (usernameParam != null) {
            userStream = userStream.filter(userEntity -> userEntity.getUsername().contains(usernameParam));
        }

        return userStream
                .skip(firstResult != null ? firstResult : 0)
                .limit(maxResults != null ? maxResults : Integer.MAX_VALUE)
                .map(userEntity -> new MyUserAdapter(session, realm, model, userEntity));
    }

    @Override
    public Stream<UserModel> getGroupMembersStream(RealmModel realm, GroupModel group, Integer firstResult, Integer maxResults) {
        return Stream.empty();
    }

    @Override
    public Stream<UserModel> searchForUserByUserAttributeStream(RealmModel realm, String attrName, String attrValue) {
        if (UserModel.USERNAME.equals(attrName)) {
            return fakeDb.values().stream()
                    .filter(userEntity -> userEntity.getUsername().equals(attrValue))
                    .map(userEntity -> new MyUserAdapter(session, realm, model, userEntity));
        }
        return Stream.empty();
    }

    @Override
    public void close() {
        // Nothing to close in this demo
    }
}
