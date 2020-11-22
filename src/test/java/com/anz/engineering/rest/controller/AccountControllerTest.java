package com.anz.engineering.rest.controller;

import com.anz.engineering.entity.Account;
import com.anz.engineering.entity.Customer;
import com.anz.engineering.service.AccountService;
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
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Test class for AccountController
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void givenCustomerId_whenGetAccounts_thenReturnJsonArray()
            throws Exception {

        Customer customer = new Customer();
        customer.setId(123456l);
        customer.setFirstName("Ravi");
        customer.setLastName("Shankar");
        customer.setEmailId("Ravis@test.com");
        customer.setAddress("Pseudo Address");

        Account account = new Account();
        account.setName("AUDRavis895");
        account.setAvailableBal(new BigDecimal(8750.95));
        account.setCurrency("AUD");
        account.setType("Current");
        account.setCustomer(customer);

        List<Account> allAccounts = Arrays.asList(account);

        given(accountService.findAllAccountsByCustomerId(customer.getId())).willReturn(allAccounts);

        mvc.perform(get("/api/account/getaccounts")
                .param("customerId", customer.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(account.getName())));
    }

}
