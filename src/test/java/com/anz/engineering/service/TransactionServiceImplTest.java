package com.anz.engineering.service;

import com.anz.engineering.entity.Account;
import com.anz.engineering.entity.Customer;
import com.anz.engineering.entity.Transaction;
import com.anz.engineering.repository.TransactionRepository;
import com.anz.engineering.service.impl.TransactionServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Test class for TransactionServiceImpl
 */
@RunWith(SpringRunner.class)
public class TransactionServiceImplTest {

    @TestConfiguration
    static class TransactionServiceImplTestContextConfiguration {

        @Bean
        public TransactionService transactionService() {
            return new TransactionServiceImpl();
        }
    }

    @Autowired
    private TransactionService transactionService;

    @MockBean
    private TransactionRepository transactionRepository;

    @Before
    public void setUp() {

        Customer customer = new Customer();
        customer.setId(987654l);
        customer.setFirstName("Ravi");
        customer.setLastName("Shankar");
        customer.setEmailId("ravis@test.com");
        customer.setAddress("pseudo Address");

        Account account = new Account();
        account.setNumber(123456l);
        account.setName("AUDRavis456");
        account.setAvailableBal(new BigDecimal(8750.95));
        account.setCurrency("AUD");
        account.setType("Current");
        account.setCustomer(customer);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setCreditAmt(new BigDecimal(5642.25));
        transaction.setDebitCredit("Credit");
        transaction.setValueDate(LocalDate.now());

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Mockito.when(transactionRepository.findAllTransactions(account.getNumber()))
                .thenReturn(transactions);
    }

    @Test
    public void whenValidAccountNumber_thenTransactionsShouldBeFound() {
        Long acctNumber = 123456l;
        List<Transaction> found = transactionService.findAll(acctNumber);

        Assert.assertEquals(found.get(0).getAccount().getNumber(), acctNumber);
    }
}
