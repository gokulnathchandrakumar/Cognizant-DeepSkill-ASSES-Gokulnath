package com.cognizant.loan.controller;

import com.cognizant.loan.LoanDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoanController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(LoanController.class);


    @GetMapping("/loans/{number}")
    public LoanDetails getLoanDetails(
            @PathVariable String number) {

        // START LOG
        LOGGER.info("Start getLoanDetails");
        LOGGER.debug("Fetching loan details for number: {}", number);

        // Create dummy loan details — no DB required
        LoanDetails loan = new LoanDetails(
                "H00987987972342",  // hardcoded dummy loan number
                "car",              // loan type
                400000L,            // total loan amount
                3258L,              // monthly EMI
                18                  // tenure in months
        );

        LOGGER.debug("Returning loan: {}", loan);

        // END LOG
        LOGGER.info("End getLoanDetails");

        return loan;
    }
}