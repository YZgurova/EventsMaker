package com.EventsMaker.v1.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User
{
    public final Integer id;

    public final String username;
    public final String email;

    @JsonCreator
    public User(
            @JsonProperty("id") Integer id,
            @JsonProperty("username") String username,
            @JsonProperty("email") String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
