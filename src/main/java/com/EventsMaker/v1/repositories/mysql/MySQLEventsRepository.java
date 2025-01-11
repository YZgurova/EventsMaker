package com.EventsMaker.v1.repositories.mysql;

import com.EventsMaker.v1.models.Event;
import com.EventsMaker.v1.repositories.EventsRepository;
import com.EventsMaker.v1.repositories.entities.EventEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public class MySQLEventsRepository implements EventsRepository
{

    private final TransactionTemplate txTemplate;
    private final JdbcTemplate jdbc;

    public MySQLEventsRepository(TransactionTemplate txTemplate, JdbcTemplate jdbcT) {
        this.txTemplate = txTemplate;
        this.jdbc = jdbcT;
    }

    @Override
    public EventEntity createEvent(final String title,
                                   final String description,
                                   final int authorId,
                                   final BigDecimal price)
    {
        return txTemplate.execute(status -> {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbc.update(conn -> {
                PreparedStatement ps = conn.prepareStatement(
						Queries.INSERT_EVENT, Statement.RETURN_GENERATED_KEYS
                );
                ps.setString(1,title);
                ps.setString(2,description);
                ps.setInt(3,authorId);
                ps.setBigDecimal(4,price);
                return ps;
            }, keyHolder);
            int id = Objects.requireNonNull(keyHolder.getKey().intValue());
            return getEvent(id);
        });
    }

    @Override
    public EventEntity addBookedEvent(final int userId,
                                      final int eventId)
    {
        return txTemplate.execute(status -> {
            jdbc.update(conn -> {
                PreparedStatement ps = conn.prepareStatement(
						Queries.ADD_BOOKED_EVENT, Statement.RETURN_GENERATED_KEYS
                );
                ps.setInt(1,userId);
                ps.setInt(2,eventId);
                return ps;
            });
            return getEvent(eventId);
        });
    }

    @Override
    public List<EventEntity> getAllEvents()
    {
        return jdbc.query(Queries.LIST_ALL_EVENTS, (rs, rowNum) -> fromResultSet(rs));
    }

    @Override
    public List<EventEntity> listOwnEvents(final int userId)
    {
        return jdbc.query(Queries.LIST_OWN_EVENTS, (rs, rowNum) -> fromResultSet(rs), userId);
    }

    @Override
    public List<EventEntity> listBookedEvents(final int userId)
    {
        return jdbc.query(Queries.LIST_BOOKED_EVENTS, (rs, rowNum) -> fromResultSet(rs), userId);
    }

    @Override
    public EventEntity getEvent(final int eventId)
    {
        return jdbc.queryForObject(Queries.GET_EVENT, (rs, rowNum) -> fromResultSet(rs), eventId);
    }

    @Override
    public void deleteEvent(int id) {
        txTemplate.execute(status -> {
            jdbc.update(Queries.DELETE_EVENT, id);
            return null;
        });
    }

    private EventEntity fromResultSet(ResultSet rs) throws SQLException {
        return new EventEntity(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("author"),
                rs.getBigDecimal("price")
        );
    }

    private static class Queries {
        public static final String INSERT_EVENT =
                "INSERT INTO event (title, description, author_id, price) VALUES (?,?,?,?)";

        public static final String ADD_BOOKED_EVENT =
                "INSERT INTO booked_event_to_user (user_id, event_id) VALUES (?,?)";

        public static final String GET_EVENT = """
                        SELECT e.id, e.title, e.description, u.username as author, e.price,
                        FROM event as e
                        JOIN user as u
                        ON e.author_id=u.id
                        WHERE e.id=?;""";
        public static final String LIST_ALL_EVENTS = """
                        SELECT e.id, e.title, e.description, u.username as author, e.price,
                        FROM event as e
                        JOIN user as u
                        ON e.author_id=u.id;""";

        public static final String LIST_BOOKED_EVENTS = """
                SELECT e.id, e.title, e.description, u.username as author, e.price,
                FROM event as e
                JOIN user as u
                ON e.author_id=u.id
                JOIN booked_event_to_user as b
                ON b.event_id=e.id
                WHERE b.user_id=?;""";

        public static final String LIST_OWN_EVENTS= """
                SELECT e.id, e.title, e.description, u.username as author, e.price,
                FROM event as e
                JOIN user as u
                ON e.author_id = u.id
                WHERE u.id=?;""";

        public static final String DELETE_EVENT =
                "DELETE FROM event WHERE id=?";
    }
}
