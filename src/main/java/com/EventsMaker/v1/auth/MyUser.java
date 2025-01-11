package com.EventsMaker.v1.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;

public class MyUser extends User {
    public final Integer id;

    public MyUser(Integer id, String username, String password) {
        super(username, password, new ArrayList<>());
        this.id = id;
    }
}
