package com.EventsMaker.v1.repositories;

import com.EventsMaker.v1.repositories.entities.UserEntity;

import java.util.List;

public interface UserRepository
{
    UserEntity createUser(
            Integer id, String username, String email,
            String password);

    UserEntity getUserByUsername(String username);

    UserEntity getUser(int id);

}
