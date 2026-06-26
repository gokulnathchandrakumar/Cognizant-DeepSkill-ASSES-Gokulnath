package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * OrmLearnApplication — Main entry point
 *
 * @SpringBootApplication is a shortcut for THREE annotations:
 * 1. @Configuration     — this class can define @Bean methods
 * 2. @EnableAutoConfiguration — Spring Boot auto-configures
 *                               DataSource, JPA, Hibernate etc.
 *                               based on pom.xml dependencies
 * 3. @ComponentScan     — scans com.cognizant.ormlearn package
 *                         for @Component, @Service, @Repository
 */
@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(OrmLearnApplication.class);

    // Static reference to CountryService
    // (set from ApplicationContext after Spring starts)
    private static CountryService countryService;

    public static void main(String[] args) {

        // SpringApplication.run() boots the entire Spring context:
        // 1. Creates ApplicationContext
        // 2. Scans for @Component, @Service, @Repository, @Entity
        // 3. Configures DataSource using application.properties
        // 4. Creates Hibernate SessionFactory
        // 5. Returns the ApplicationContext
        ApplicationContext context =
                SpringApplication.run(OrmLearnApplication.class, args);

        // Get CountryService bean from the context
        countryService = context.getBean(CountryService.class);

        LOGGER.info("Inside main");

        // Run all test methods
        testGetAllCountries();
        testGetCountryByCode();
        testAddCountry();
        testUpdateCountry();
        testDeleteCountry();
        testSearchCountries();
    }

    // ============================================================
    // Test Method 1: Get All Countries
    // ============================================================
    private static void testGetAllCountries() {
        LOGGER.info("Start testGetAllCountries");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("countries={}", countries);
        LOGGER.info("End testGetAllCountries");
        System.out.println("\n--- All Countries ---");
        countries.forEach(c ->
            System.out.printf("  [%s] %s%n", c.getCode(), c.getName())
        );
    }

    // ============================================================
    // Test Method 2: Get Country By Code
    // ============================================================
    private static void testGetCountryByCode() {
        LOGGER.info("Start testGetCountryByCode");
        Country country = countryService.getCountryByCode("IN");
        LOGGER.debug("country={}", country);
        LOGGER.info("End testGetCountryByCode");
        System.out.println("\n--- Get Country by Code 'IN' ---");
        System.out.println("  Found: " + country);
    }

    // ============================================================
    // Test Method 3: Add New Country
    // ============================================================
    private static void testAddCountry() {
        LOGGER.info("Start testAddCountry");
        Country newCountry = new Country("JP", "Japan");
        countryService.addCountry(newCountry);
        LOGGER.info("End testAddCountry");
        System.out.println("\n--- Added New Country ---");
        System.out.println("  Added: " + newCountry);
    }

    // ============================================================
    // Test Method 4: Update Country
    // ============================================================
    private static void testUpdateCountry() {
        LOGGER.info("Start testUpdateCountry");
        countryService.updateCountry("US",
                "United States of America (Updated)");
        LOGGER.info("End testUpdateCountry");
        System.out.println("\n--- Updated Country ---");
        System.out.println("  US name updated successfully");
    }

    // ============================================================
    // Test Method 5: Delete Country
    // ============================================================
    private static void testDeleteCountry() {
        LOGGER.info("Start testDeleteCountry");
        countryService.deleteCountry("JP");
        LOGGER.info("End testDeleteCountry");
        System.out.println("\n--- Deleted Country ---");
        System.out.println("  JP deleted successfully");
    }

    // ============================================================
    // Test Method 6: Search Countries
    // ============================================================
    private static void testSearchCountries() {
        LOGGER.info("Start testSearchCountries");
        List<Country> results =
                countryService.searchCountries("United");
        LOGGER.debug("search results={}", results);
        LOGGER.info("End testSearchCountries");
        System.out.println("\n--- Search Results for 'United' ---");
        results.forEach(c ->
            System.out.printf("  [%s] %s%n", c.getCode(), c.getName())
        );
    }
}
