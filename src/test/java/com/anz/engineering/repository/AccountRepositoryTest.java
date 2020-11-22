package com.anz.engineering.repository;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.anz.engineering.entity.Account;
import com.anz.engineering.entity.Customer;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test class for AccountRepository.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void whenFindByCustomerId_shouldReturnAccounts() {

        Customer customer = new Customer();
        customer.setFirstName("Ravi");
        customer.setLastName("Shankar");
        customer.setEmailId("ravis@test.com");
        customer.setAddress("Pseudo Address");
        entityManager.persist(customer);

        Account account = new Account();
        account.setName("AUDRavi456");
        account.setAvailableBal(new BigDecimal(8750.95));
        account.setCurrency("AUD");
        account.setType("Current");
        account.setCustomer(customer);

        entityManager.persist(account);

        List<Account> found  = accountRepository.findAllAccountsByCustomerId(customer.getId());

        assertEquals(found.size(), 1);
    }
}
