package com.EventsMaker.v1.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Authentication
{

    public final User user;
    public final String token;

    public Authentication(@JsonProperty ("user") User user, @JsonProperty ("token") String token) {
        this.user = user;
        this.token = token;
    }
}
