package com.EventsMaker.v1.services;

import com.EventsMaker.v1.models.Credentials;
import com.EventsMaker.v1.models.Event;
import com.EventsMaker.v1.models.User;
import com.EventsMaker.v1.repositories.entities.CredentialsEntity;
import com.EventsMaker.v1.repositories.entities.EventEntity;
import com.EventsMaker.v1.repositories.entities.UserEntity;

public class Mappers
{
    public static User fromUserEntity(UserEntity user) {
        return new User(user.id, user.username, user.email);
    }

    public static Event fromEventEntity(EventEntity eventEntity) {
        return new Event(eventEntity.id, eventEntity.title, eventEntity.description, eventEntity.author, eventEntity.price);
    }

    public static Credentials fromCredentialsEntity(CredentialsEntity credentialsEntity) {
        return new Credentials(credentialsEntity.id, credentialsEntity.username);
    }
}
