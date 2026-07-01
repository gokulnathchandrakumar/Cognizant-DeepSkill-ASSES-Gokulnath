package com.cognizant.spring_learn.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SecurityConfig — configures which endpoints require security
 *
 * We manually decode the Authorization header in the controller,
 * so /authenticate itself is left open (permitAll) at the
 * Spring Security filter level — Spring Security's own Basic Auth
 * filter is not used to gate this endpoint.
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/authenticate").permitAll()
                    .anyRequest().permitAll()  // keep other endpoints open for now
            );

        return http.build();
    }
}