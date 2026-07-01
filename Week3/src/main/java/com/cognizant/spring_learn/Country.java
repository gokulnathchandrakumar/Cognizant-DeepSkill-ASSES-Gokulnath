package com.cognizant.spring_learn;

/**
 * Country — Model/Entity class for Country data
 *
 * ================================================================
 * Why we need this class:
 * ================================================================
 * 1. Represents a Country entity with properties
 * 2. Jackson (JSON library) inspects this class
 * 3. Finds all private fields with getters/setters
 * 4. Automatically converts to JSON:
 *    new Country("IN", "India")
 *    ↓
 *    {"code":"IN","name":"India"}
 *
 * Rules for JSON serialization:
 * - Class must be public
 * - Fields must have public getters (setters optional for serialization)
 * - No special annotations needed — Jackson uses reflection
 * - Field names become JSON property names automatically
 * ================================================================
 */
public class Country {

    // Private fields — Jackson will serialize these via getters
    private String code;      // country code (e.g., "IN")
    private String name;      // country name (e.g., "India")

    /**
     * Default no-arg constructor
     * Required by Jackson for deserialization
     * (when converting JSON to Java objects)
     */
    public Country() {
    }

    /**
     * Parameterized constructor
     * Used by Spring XML config when creating beans
     */
    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * Getters — REQUIRED for JSON serialization
     * Jackson calls these to extract values from the object
     * When serializing: gets code → puts in JSON as "code": value
     */
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    /**
     * Setters — helpful for XML config <property> injection
     * Also used when deserializing JSON to Java objects
     */
    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * toString — useful for logging/debugging
     * NOT used for JSON serialization
     */
    @Override
    public String toString() {
        return "Country{" +
               "code='" + code + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}