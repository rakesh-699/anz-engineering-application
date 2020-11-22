package com.anz.engineering.service.impl;

import com.anz.engineering.entity.Account;
import com.anz.engineering.repository.AccountRepository;
import com.anz.engineering.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AccountService implementation.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     *
     * @param customerId
     * @return
     */
    @Override
    public List<Account> findAllAccountsByCustomerId(final Long customerId) {
        return accountRepository.findAllAccountsByCustomerId(customerId);
    }
}
