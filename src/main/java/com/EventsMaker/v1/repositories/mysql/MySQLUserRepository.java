package com.EventsMaker.v1.repositories.mysql;

import com.EventsMaker.v1.repositories.UserRepository;
import com.EventsMaker.v1.repositories.entities.UserEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLUserRepository implements UserRepository {

    private final TransactionTemplate txTemplate;
    private final JdbcTemplate jdbc;

    public MySQLUserRepository(TransactionTemplate txTemplate, JdbcTemplate jdbc) {
        this.txTemplate = txTemplate;
        this.jdbc = jdbc;
    }

    @Override
    public UserEntity createUser(Integer id, String username, String email, String password) {
        return txTemplate.execute(status -> {
            jdbc.update(conn -> {
                PreparedStatement ps = conn.prepareStatement(
						Queries.INSERT_USER, Statement.RETURN_GENERATED_KEYS
                );
                ps.setInt(1,id);
                ps.setString(2,username);
                ps.setString(3, email);
                ps.setString(4, password);
                return ps;
            });
            return getUser(id);
        });
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        return jdbc.queryForObject(Queries.GET_USER_BY_USERNAME, (rs, rowNum) -> fromResultSet(rs), username);
    }

    @Override
    public UserEntity getUser(int id) {
        return jdbc.queryForObject(Queries.GET_USER, (rs, rowNum) -> fromResultSet(rs), id);
    }
    private UserEntity fromResultSet(ResultSet rs) throws SQLException {
        return new UserEntity(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password")
        );
    }

    static class Queries {
        public static final String INSERT_USER =
                "INSERT INTO user (username, email, password)" +
                        "VALUES (?,?,?)";
        public static final String GET_USER =
                """
                select u.id, u.first_name, u.last_name,u.username, u.email,u.password
                FROM user as u
                WHERE u.id=?;""";

        public static final String GET_USER_BY_USERNAME =
                """
                select u.id, u.first_name, u.last_name,u.username, u.email,u.password
                FROM user as u
                WHERE u.username=?;""";

    }
}
