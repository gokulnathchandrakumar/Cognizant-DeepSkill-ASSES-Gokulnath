package com.cognizant.ormlearn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Country — JPA Entity class
 *
 * @Entity  tells JPA/Hibernate: "This class maps to a database table"
 * @Table   specifies WHICH table it maps to
 *
 * Hibernate reads this class and knows:
 * - Country objects are stored in the 'country' table
 * - 'code' field = co_code column (primary key)
 * - 'name' field = co_name column
 */
@Entity
@Table(name = "country")
public class Country {

    /**
     * @Id     = marks this as the PRIMARY KEY field
     * @Column = maps this Java field to the database column
     *           name="co_code" because our column is named co_code
     *           not just "code"
     */
    @Id
    @Column(name = "co_code")
    private String code;

    @Column(name = "co_name")
    private String name;

    // Default constructor — REQUIRED by JPA/Hibernate
    // Hibernate uses reflection to create instances — needs no-arg constructor
    public Country() {
    }

    // Parameterized constructor — convenient for creating objects in code
    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    // Getters and Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // toString — used by LOGGER.debug("countries={}", countries)
    // to print readable output instead of memory address
    @Override
    public String toString() {
        return "Country{" +
               "code='" + code + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}
