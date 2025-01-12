package com.EventsMaker.v1.beans;

import com.EventsMaker.v1.repositories.*;
import com.EventsMaker.v1.repositories.mysql.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class RepositoryBeans
{

    @Bean
    public UserRepository userRepository(
            TransactionTemplate txTemplate, JdbcTemplate jdbcTemplate) {
        return new MySQLUserRepository(txTemplate, jdbcTemplate);
    }

    @Bean
    public EventsRepository eventsRepository(
            TransactionTemplate txTemplate, JdbcTemplate jdbcTemplate) {
        return new MySQLEventsRepository(txTemplate, jdbcTemplate);
    }

    @Bean
    public CredentialsRepository credentialsRepository(
            TransactionTemplate txTemplate, JdbcTemplate jdbcTemplate) {
        return new MySQLCredentialsRepository(txTemplate, jdbcTemplate);
    }

    @Bean
    public TransactionsRepository transactionsRepository(TransactionTemplate txTemplate, JdbcTemplate jdbcTemplate) {
        return new MySQLTransactionRepository(txTemplate, jdbcTemplate);
    }
}
