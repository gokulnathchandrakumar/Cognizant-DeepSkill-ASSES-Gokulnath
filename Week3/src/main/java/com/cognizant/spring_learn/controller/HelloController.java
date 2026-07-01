package com.cognizant.spring_learn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController — First REST Web Service
 *
 * ================================================================
 * @RestController annotation explained:
 * ================================================================
 * Combination of TWO annotations:
 *
 * 1. @Controller
 *    - Marks this class as handling HTTP requests
 *    - Spring creates a bean from this class
 *
 * 2. @ResponseBody (implied by @RestController)
 *    - All method return values are directly serialized to response
 *    - NOT treated as view/template names
 *    - Returned string becomes the response body
 *    - Perfect for REST APIs (JSON/plain text response)
 *
 * Without @ResponseBody, Spring would look for a view named
 * "Hello World!!" — it would fail since no such view exists.
 *
 * With @RestController: return value becomes HTTP response body
 * ================================================================
 */
@RestController
public class HelloController {

    /**
     * Logger setup — tied to this specific class
     * LoggerFactory.getLogger(HelloController.class)
     * Every log message shows "HelloController" as the source
     */
    private static final Logger LOGGER =
            LoggerFactory.getLogger(HelloController.class);

    /**
     * GET /hello endpoint
     *
     * ================================================================
     * @GetMapping annotation explained:
     * ================================================================
     * @GetMapping("/hello") = HTTP GET request to URL /hello
     * Maps directly to http://localhost:8083/hello
     *
     * Spring intercepts GET requests to /hello
     * and calls this method automatically
     *
     * Method signature MUST be:
     * - public (accessible to Spring)
     * - returns String (or any serializable type for JSON)
     * - no @PathVariable or @RequestParam needed for simple case
     * ================================================================
     */
    @GetMapping("/hello")
    public String sayHello() {

        // START LOG — as per exercise requirement
        LOGGER.info("Start sayHello");
        LOGGER.debug("Received GET request on /hello endpoint");

        // Simulate some processing (optional)
        String response = "Hello World!!";
        LOGGER.debug("Prepared response: {}", response);

        // Return the hardcoded string — @RestController makes it
        // the response body, NOT a view name
        return response;

        // END LOG would go here, but we must return first
        // (so it's better placed AFTER logging the actual return)
    }

    /**
     * Alternative with explicit end log using try-finally:
     *
     * ================================================================
     * If you want BOTH start and end logs explicitly:
     * ================================================================
     */
    @GetMapping("/hello-v2")
    public String sayHelloWithEndLog() {
        LOGGER.info("Start sayHelloWithEndLog");

        try {
            String response = "Hello World!!";
            LOGGER.debug("Response prepared: {}", response);
            return response;
        } finally {
            LOGGER.info("End sayHelloWithEndLog");
        }
    }
}