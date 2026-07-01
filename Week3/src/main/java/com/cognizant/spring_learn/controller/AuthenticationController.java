package com.cognizant.spring_learn.controller;

import com.cognizant.spring_learn.security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * AuthenticationController — Step 1 of JWT process
 *
 * 1. Reads the "Authorization" header (sent via curl -u user:pwd)
 * 2. Decodes Base64 to extract username:password
 * 3. Validates credentials
 * 4. Generates and returns a JWT token
 */
@RestController
public class AuthenticationController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private JwtUtil jwtUtil;

    // Hardcoded valid credentials for this hands-on
    private static final String VALID_USERNAME = "user";
    private static final String VALID_PASSWORD = "pwd";

    @GetMapping("/authenticate")
    public ResponseEntity<?> authenticate(
            @RequestHeader("Authorization") String authHeader) {

        LOGGER.info("Start authenticate - Authorization header received");

        // ============================================================
        // STEP 1: Validate header format
        // ============================================================
        // curl -u sends: "Authorization: Basic base64(user:pwd)"
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            LOGGER.warn("Missing or malformed Authorization header");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Missing or invalid Authorization header");
        }

        // ============================================================
        // STEP 2: Decode Base64 credentials
        // ============================================================
        String base64Credentials = authHeader.substring("Basic ".length());
        String decoded = new String(Base64.getDecoder().decode(base64Credentials));
        // decoded = "user:pwd"

        LOGGER.debug("Decoded credentials: {}", decoded);

        String[] parts = decoded.split(":", 2);
        String username = parts[0];
        String password = parts.length > 1 ? parts[1] : "";

        // ============================================================
        // STEP 3: Validate username/password
        // ============================================================
        if (!VALID_USERNAME.equals(username) || !VALID_PASSWORD.equals(password)) {
            LOGGER.warn("Invalid credentials for username: {}", username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }

        // ============================================================
        // STEP 4: Generate JWT token
        // ============================================================
        String token = jwtUtil.generateToken(username);
        LOGGER.info("Token generated successfully for user: {}", username);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}