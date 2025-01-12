package com.EventsMaker.v1.repositories;

import com.EventsMaker.v1.repositories.entities.TransactionEntity;

public interface TransactionsRepository
{
    TransactionEntity createTransaction(int userId, String transaction, String status);
    TransactionEntity getTransaction(int transactionId);
}
