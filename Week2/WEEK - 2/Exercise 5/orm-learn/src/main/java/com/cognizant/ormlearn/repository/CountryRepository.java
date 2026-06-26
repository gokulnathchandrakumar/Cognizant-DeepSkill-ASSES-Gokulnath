package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CountryRepository — Data Access Layer
 *
 * By extending JpaRepository<Country, String>:
 * - Country = the Entity this repository manages
 * - String  = the type of the Primary Key (co_code is a String)
 *
 * Spring Data JPA automatically generates implementations for:
 * - findAll()          → SELECT * FROM country
 * - findById(code)     → SELECT * FROM country WHERE co_code = ?
 * - save(country)      → INSERT or UPDATE
 * - deleteById(code)   → DELETE FROM country WHERE co_code = ?
 * - count()            → SELECT COUNT(*) FROM country
 * - existsById(code)   → SELECT 1 FROM country WHERE co_code = ?
 *
 * You write ZERO SQL — Spring Data JPA generates it all.
 */
@Repository
public interface CountryRepository
        extends JpaRepository<Country, String> {

    // No code needed for basic CRUD operations!
    // JpaRepository provides them all automatically.

    // You CAN add custom query methods using Spring Data
    // naming conventions — Spring generates the SQL automatically:

    // Find countries whose name contains a keyword (case-insensitive)
    // SQL: SELECT * FROM country WHERE co_name LIKE '%keyword%'
    java.util.List<Country> findByNameContaining(String keyword);

    // Find country by exact name
    // SQL: SELECT * FROM country WHERE co_name = ?
    java.util.Optional<Country> findByName(String name);

}
