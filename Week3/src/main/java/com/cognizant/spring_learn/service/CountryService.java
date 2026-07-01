package com.cognizant.spring_learn.service;

import com.cognizant.spring_learn.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * CountryService — Business logic layer for Country lookups
 *
 * Loads all Country beans defined in country-config.xml
 * and finds a match by code, ignoring case.
 */
@Service
public class CountryService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(CountryService.class);

    public Country getCountry(String code) {

        LOGGER.info("Start getCountry - searching for code: {}", code);

        // STEP 1: Load Spring XML context (same file as before)
        ApplicationContext context =
                new ClassPathXmlApplicationContext("country-config.xml");

        // STEP 2: Get ALL Country beans defined in the XML
        // getBeansOfType returns a Map<beanId, beanInstance>
        // We only need the values (the actual Country objects)
        Collection<Country> countries =
                context.getBeansOfType(Country.class).values();

        LOGGER.debug("Loaded {} countries from XML config", countries.size());

        // STEP 3: Case-insensitive match using a lambda/stream
        Country result = countries.stream()
                .filter(country -> country.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Country not found for code: " + code));

        LOGGER.info("End getCountry - found: {}", result);
        return result;
    }
}