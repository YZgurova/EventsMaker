package com.EventsMaker.v1.repositories;

import com.EventsMaker.v1.repositories.entities.CredentialsEntity;

import java.util.Optional;

public interface CredentialsRepository
{
    CredentialsEntity createCredentials(String username, String passwordHash);

    String createAuthToken(Integer credentialsId, String authToken);
    Optional<CredentialsEntity> getCredentials(String username);
    Optional<CredentialsEntity> getCredentialsByAuthToken(String authToken);
}
