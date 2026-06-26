package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * CountryService — Business Logic Layer
 *
 * @Service  = tells Spring to manage this as a Bean
 *             (specialization of @Component for service classes)
 *
 * @Autowired = tells Spring to inject CountryRepository here
 *              Spring finds the CountryRepository bean and
 *              automatically wires it in — no 'new' keyword needed
 *
 * @Transactional = wraps the method in a database transaction
 *                  If the method completes: COMMIT
 *                  If any exception occurs: ROLLBACK
 */
@Service
public class CountryService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(CountryService.class);

    // @Autowired — Spring injects CountryRepository automatically
    // This is FIELD INJECTION (the convenient way for services)
    @Autowired
    private CountryRepository countryRepository;

    /**
     * Get all countries from the database
     * @Transactional ensures this runs inside a DB transaction
     */
    @Transactional
    public List<Country> getAllCountries() {
        LOGGER.debug("Start getAllCountries");

        // findAll() is provided FREE by JpaRepository
        // Hibernate generates: SELECT co_code, co_name FROM country
        List<Country> countries = countryRepository.findAll();

        LOGGER.debug("getAllCountries result: {}", countries);
        return countries;
    }

    /**
     * Find a country by its code
     */
    @Transactional
    public Country getCountryByCode(String code) {
        LOGGER.debug("Start getCountryByCode, code={}", code);

        // findById returns Optional — handles the case where code doesn't exist
        Optional<Country> country = countryRepository.findById(code);

        // orElse(null) returns null if not found
        // In production, you'd throw a custom exception instead
        return country.orElse(null);
    }

    /**
     * Save a new country (INSERT)
     */
    @Transactional
    public void addCountry(Country country) {
        LOGGER.debug("Start addCountry, country={}", country);
        countryRepository.save(country);
        LOGGER.debug("Country saved successfully");
    }

    /**
     * Update an existing country
     * save() in JpaRepository does INSERT if new, UPDATE if exists
     * (checks if entity has an ID that already exists in DB)
     */
    @Transactional
    public void updateCountry(String code, String newName) {
        LOGGER.debug("Start updateCountry, code={}", code);
        Optional<Country> existing = countryRepository.findById(code);
        existing.ifPresent(country -> {
            country.setName(newName);
            countryRepository.save(country);
            LOGGER.debug("Country updated: {}", country);
        });
    }

    /**
     * Delete a country by code
     */
    @Transactional
    public void deleteCountry(String code) {
        LOGGER.debug("Start deleteCountry, code={}", code);
        countryRepository.deleteById(code);
        LOGGER.debug("Country deleted, code={}", code);
    }

    /**
     * Search countries by partial name
     */
    @Transactional
    public List<Country> searchCountries(String keyword) {
        LOGGER.debug("Start searchCountries, keyword={}", keyword);
        return countryRepository.findByNameContaining(keyword);
    }
}
