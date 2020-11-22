package com.anz.engineering.rest.controller;

import com.anz.engineering.service.TransactionService;
import com.anz.engineering.entity.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Controller class for Transaction REST API endpoints.
 */
@RestController
@Slf4j
@RequestMapping("api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     *
     * @param accountNumber
     * @return
     * @throws ResponseStatusException
     */
    @RequestMapping(method = RequestMethod.GET, path = "/gettransactions", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Transaction> getAllTransactions(@RequestParam Long accountNumber) throws ResponseStatusException {

        List<Transaction> transactions = null;
        log.info(String.format("Request to get all transactions for account number: %d ", accountNumber));

        try {
            transactions = transactionService.findAll(accountNumber);
            log.info(String.format("Found %d transactions for account number: %d ", transactions.size(), accountNumber));
        } catch (Exception e) {
            log.error(String.format("Error occurred while getting transactions for account number: %d ", accountNumber), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching transactions data");
        }
        return transactions;
    }

}
