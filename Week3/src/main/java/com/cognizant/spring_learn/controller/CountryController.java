package com.cognizant.spring_learn.controller;

import com.cognizant.spring_learn.Country;
import com.cognizant.spring_learn.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    @RequestMapping("/country")
    public Country getCountryIndia() {

        LOGGER.info("Start getCountryIndia");
        LOGGER.debug("Received request on /country endpoint");

        ApplicationContext context =
                new ClassPathXmlApplicationContext("country-config.xml");

        LOGGER.debug("Spring XML Context loaded from country-config.xml");

        Country india = context.getBean("india", Country.class);

        LOGGER.debug("India bean retrieved: {}", india);

        LOGGER.debug("Returning country data: code={}, name={}",
                india.getCode(), india.getName());

        LOGGER.info("End getCountryIndia");
        return india;
    }

    @RequestMapping("/country-list")
    public String getCountryList() {
        LOGGER.info("Start getCountryList");

        return "Available countries: " +
               "GET /country (India), " +
               "GET /country/usa (USA), " +
               "GET /country/uk (UK), " +
               "GET /country/australia (Australia)";
    }

    @GetMapping("/country/{code}")
    public Country getCountry(@PathVariable String code) {
        LOGGER.info("Start getCountry endpoint - code={}", code);
        Country country = countryService.getCountry(code);
        LOGGER.info("End getCountry endpoint - returning: {}", country);
        return country;
    }
}