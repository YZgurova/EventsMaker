package com.EventsMaker.v1.repositories.entities;

public class CredentialsEntity
{
    public final int id;
    public final String username;
    public final String passwordHash;

    public CredentialsEntity(int id, String username, String passwordHash) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
    }
}
