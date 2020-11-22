package com.anz.engineering.service.impl;

import com.anz.engineering.entity.Transaction;
import com.anz.engineering.repository.TransactionRepository;
import com.anz.engineering.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public List<Transaction> findAll(Long accountNumber) {
        return transactionRepository.findAllTransactions(accountNumber);
    }
}
