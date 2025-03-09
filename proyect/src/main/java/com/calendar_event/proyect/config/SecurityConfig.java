package com.calendar_event.proyect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Desactiva CSRF para APIs
                .authorizeRequests()
                .antMatchers("/api/events/**").authenticated() // Protege los endpoints de eventos
                .anyRequest().permitAll() // Permite acceso público a otros endpoints
                .and()
                .oauth2ResourceServer().jwt(); // Habilita el soporte para JWT

        return http.build();
    }
}
