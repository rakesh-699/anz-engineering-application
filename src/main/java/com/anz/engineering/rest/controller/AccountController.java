package com.anz.engineering.rest.controller;

import com.anz.engineering.entity.Account;
import com.anz.engineering.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Controller class for Account REST API endpoints.
 */
@RestController
@Slf4j
@RequestMapping("api/account")
public class AccountController {
		
    @Autowired
    private AccountService accountService;

    /**
     *
     * @param customerId
     * @return
     * @throws ResponseStatusException
     */
    @RequestMapping(method = RequestMethod.GET, path = "/getaccounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Account> getAllAccounts(@RequestParam(value = "customerId") Long customerId) throws ResponseStatusException {

        List<Account> accounts = null;
        log.info(String.format("Request to get all accounts for customer ID: %d ", customerId));

        try {
            accounts = accountService.findAllAccountsByCustomerId(customerId);
            log.info(String.format("Found %d accounts for customer ID: %d ", accounts.size(), customerId));

        } catch (Exception e) {
            log.error(String.format("Error occurred while getting accounts for customer ID: %d ", customerId), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching accounts data");
        }
        return accounts;
    }

}
