package com.cognizant.account.controller;

import com.cognizant.account.AccountDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AccountController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(AccountController.class);

    @GetMapping("/accounts/{number}")
    public AccountDetails getAccountDetails(
            @PathVariable String number) {

        // START LOG — required by exercise
        LOGGER.info("Start getAccountDetails");
        LOGGER.debug("Fetching account details for number: {}", number);

        // Create dummy account details — no DB required
        // In real microservice, this would call a database or
        // another service to fetch actual data
        AccountDetails account = new AccountDetails(
                "00987987973432",   // hardcoded dummy number
                "savings",          // hardcoded type
                234343              // hardcoded balance
        );

        LOGGER.debug("Returning account: {}", account);

        // END LOG — required by exercise
        LOGGER.info("End getAccountDetails");

        // @RestController + Jackson automatically converts
        // AccountDetails → JSON before sending to client
        return account;
    }
}