package com.cognizant.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AccountApplication {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(AccountApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting Account Microservice...");
        SpringApplication.run(AccountApplication.class, args);
        LOGGER.info("Account Microservice started on port 8080");
        LOGGER.info("Test URL: http://localhost:8080/accounts/00987987973432");
    }
}