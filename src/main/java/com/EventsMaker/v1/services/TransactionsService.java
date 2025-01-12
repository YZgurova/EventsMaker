package com.EventsMaker.v1.services;

import com.EventsMaker.v1.models.Transaction;
import com.EventsMaker.v1.repositories.TransactionsRepository;

public class TransactionsService
{
    TransactionsRepository repository;

    public TransactionsService(TransactionsRepository transactionsRepository) {
        this.repository = transactionsRepository;
    }

    public Transaction createTransaction(int userId, String transaction, String status) {
        return Mappers.fromTransactionEntity(repository.createTransaction(userId, transaction, status));
    }

    public Transaction getTransaction(int transactionId) {
        return Mappers.fromTransactionEntity(repository.getTransaction(transactionId));
    }
}
