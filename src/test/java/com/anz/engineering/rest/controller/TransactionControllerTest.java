package com.anz.engineering.rest.controller;

import com.anz.engineering.entity.Account;
import com.anz.engineering.entity.Customer;
import com.anz.engineering.entity.Transaction;
import com.anz.engineering.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Test class for TransactionController.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransactionService transactionService;

    @Test
    public void givenAccountNumber_whenGetTransactions_thenReturnJsonArray()
            throws Exception {

        Customer customer = new Customer();
        customer.setId(123456l);
        customer.setFirstName("Ravi");
        customer.setLastName("Shankar");
        customer.setEmailId("ravis@test.com");
        customer.setAddress("Pseudo Address");

        Account account = new Account();
        account.setNumber(96587l);
        account.setName("AUDRavis895");
        account.setAvailableBal(new BigDecimal(8750.95));
        account.setCurrency("AUD");
        account.setType("Current");
        account.setCustomer(customer);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setCreditAmt(new BigDecimal(5642.25));
        transaction.setDebitCredit("Credit");
        transaction.setValueDate(LocalDate.now());

        List<Transaction> allTransactions = Arrays.asList(transaction);

        given(transactionService.findAll(account.getNumber())).willReturn(allTransactions);

        mvc.perform(get("/api/transaction/gettransactions")
                .param("accountNumber", account.getNumber().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].account.name", is(account.getName())));
    }

}
