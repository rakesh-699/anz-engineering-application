package com.anz.engineering.service;

import com.anz.engineering.entity.Transaction;

import java.util.List;

/**
 * Interface to add Transaction service queries.
 */
public interface TransactionService {

    List<Transaction> findAll(Long accountNumber);
}
