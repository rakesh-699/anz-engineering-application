package com.anz.engineering.service;

import com.anz.engineering.entity.Account;
import com.anz.engineering.entity.Customer;
import com.anz.engineering.repository.AccountRepository;
import com.anz.engineering.service.impl.AccountServiceImpl;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Test class for AccountServiceImpl
 */
@RunWith(SpringRunner.class)
public class AccountServiceImplTest {

    @TestConfiguration
    static class AccountServiceImplTestContextConfiguration {

        @Bean
        public AccountService accountService() {
            return new AccountServiceImpl();
        }
    }

    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

    @Before
    public void setUp() {

        Customer customer = new Customer();
        customer.setId(987654l);
        customer.setFirstName("Ravi");
        customer.setLastName("Shankar");
        customer.setEmailId("ravis@test.com");
        customer.setAddress("Pseudo Address");

        Account account = new Account();
        account.setNumber(123456l);
        account.setName("AUDRavis456");
        account.setAvailableBal(new BigDecimal(8750.95));
        account.setCurrency("AUD");
        account.setType("Current");
        account.setCustomer(customer);

        List<Account> accounts = new ArrayList<>();
        accounts.add(account);

        Mockito.when(accountRepository.findAllAccountsByCustomerId(customer.getId()))
                .thenReturn(accounts);
    }

    @Test
    public void whenValidCustomerId_thenAccountsShouldBeFound() {
        Long customerId = 987654l;
        List<Account> found = accountService.findAllAccountsByCustomerId(customerId);

        Assert.assertEquals(found.get(0).getCustomer().getId(), customerId);
    }

}
