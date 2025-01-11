package com.EventsMaker.v1.services;

import com.EventsMaker.v1.models.Authentication;
import com.EventsMaker.v1.models.Credentials;
import com.EventsMaker.v1.models.User;
import com.EventsMaker.v1.repositories.CredentialsRepository;
import com.EventsMaker.v1.repositories.UserRepository;
import com.EventsMaker.v1.repositories.entities.CredentialsEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.security.SecureRandom;
import java.util.Optional;

public class AuthService
{
    private final CredentialsRepository credentialsRepository;
    private final UserRepository userRepository;
    private final TransactionTemplate transactionTemplate;

    public AuthService(CredentialsRepository credentialsRepository, UserRepository userRepository, TransactionTemplate transactionTemplate) {
        this.credentialsRepository = credentialsRepository;
        this.transactionTemplate=transactionTemplate;
        this.userRepository=userRepository;
    }

    public Authentication login(String username, String password) {
        Optional<CredentialsEntity> creds =
                credentialsRepository.getCredentials(username);
        if (creds.isEmpty() ) {
            throw new RuntimeException("invalid credentials");
        }

        User user = Mappers.fromUserEntity(userRepository.getUserByUsername(username));
        //TODO: check if password hash is equal to this from db
        return new Authentication(user, credentialsRepository.createAuthToken(
                creds.get().id, generateAuthToken()));
    }

    public void register(String username, String password, String email) {
        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        try {
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    CredentialsEntity credentials = credentialsRepository.createCredentials(username, passwordHash);
                    userRepository.createUser(credentials.id, username, email, passwordHash);

                }
            });
        } catch (TransactionException e) {
            System.out.println("Register: "+e);
            throw new RuntimeException(e);
        }
    }

    public Optional<Credentials> getCredentialsByAuthToken(String authToken) {
        Optional<CredentialsEntity> creds =
                credentialsRepository.getCredentialsByAuthToken(authToken);
        return creds.map(Mappers::fromCredentialsEntity);
    }

    private String generateAuthToken() {
        int length = 16;
        String alphabet =
                "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(length);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }

        return sb.toString();
    }
}
