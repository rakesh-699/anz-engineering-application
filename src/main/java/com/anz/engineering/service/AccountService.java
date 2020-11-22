package com.anz.engineering.service;

import com.anz.engineering.entity.Account;

import java.util.List;

/**
 * Interface to add Account service queries.
 */
public interface AccountService {

    List<Account> findAllAccountsByCustomerId(final Long customerId);
}
