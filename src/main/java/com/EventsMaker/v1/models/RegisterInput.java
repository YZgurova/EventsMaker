package com.EventsMaker.v1.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterInput
{
    public final String username;
    public final String password;
    public final String email;

    @JsonCreator
    public RegisterInput(
            @JsonProperty("username") String username,
            @JsonProperty("password") String password,
            @JsonProperty("email") String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
