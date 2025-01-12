package com.EventsMaker.v1.repositories.mysql;

import com.EventsMaker.v1.repositories.CredentialsRepository;
import com.EventsMaker.v1.repositories.entities.CredentialsEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class MySQLCredentialsRepository implements CredentialsRepository {
    private final TransactionTemplate txTemplate;
    private final JdbcTemplate jdbc;

    public MySQLCredentialsRepository(TransactionTemplate txTemplate, JdbcTemplate jdbc) {
        this.txTemplate = txTemplate;
        this.jdbc = jdbc;
    }

    @Override
    public CredentialsEntity createCredentials(String username, String passwordHash) {
        return txTemplate.execute(status -> {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbc.update(conn -> {
                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO credentials (username, password_hash) VALUES (?, ?)",
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, username);
                ps.setString(2, passwordHash);
                return ps;
            }, keyHolder);

            int id = Objects.requireNonNull(keyHolder.getKey()).intValue();

            return new CredentialsEntity(id, username, passwordHash);
        });
    }

    @Override
    public String createAuthToken(Integer credentialsId, String authToken) {
        jdbc.update("INSERT INTO auth_tokens (token, credentials_id) " +
                "VALUES (?, ?)", authToken, credentialsId);
        return authToken;
    }

    @Override
    public Optional<CredentialsEntity> getCredentials(String username) {
        return txTemplate.execute(status -> {
            Map<String, Object> creds = jdbc.queryForMap(
                    "SELECT id, username, password_hash " +
                            "FROM credentials WHERE username = ?", username);

            return Optional.of(
                    new CredentialsEntity(
                            (int)creds.get("id"),
                            (String) creds.get("username"),
                            (String) creds.get("password_hash")));
        });
    }

    @Override
    public Optional<CredentialsEntity> getCredentialsByAuthToken(String authToken) {
        return txTemplate.execute(status -> {
            Map<String, Object> creds = jdbc.queryForMap(
                    "SELECT c.id as id, c.username as username, c.password_hash as password_hash " +
                            "FROM credentials c " +
                            "JOIN auth_tokens a ON c.id = a.credentials_id " +
                            "WHERE a.token = ?", authToken);

            return Optional.of(
                    new CredentialsEntity(
                            (int)creds.get("id"),
                            (String) creds.get("username"),
                            (String) creds.get("password_hash")));
        });
    }
}
