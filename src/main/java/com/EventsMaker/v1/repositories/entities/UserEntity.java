package com.EventsMaker.v1.repositories.entities;

public class UserEntity
{
    public final int id;
    public final String username;
    public final String email;
    public final String password_hash;

    public UserEntity(int id, String username, String email, String password) {
        this.id=id;
        this.username = username;
        this.email = email;
        this.password_hash = password;
    }
}
