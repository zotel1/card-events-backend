package com.calendar_event.proyect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Desactiva CSRF para APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/events/**").authenticated() // Protege los endpoints de eventos
                        .anyRequest().permitAll() // Permite acceso público a otros endpoints
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt()); // Habilita el soporte para JWT

        return http.build();
    }
}