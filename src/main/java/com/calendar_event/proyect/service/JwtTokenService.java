package com.calendar_event.proyect.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenService {
    private final String apiSecret = "TExBVkVfTVVZX1NFQ1JFVEzE3Zmxu7BSGSJx72BSBXM";

    private static final long ACCESS_TOKEN_VALIDITY = 1000 * 60 * 15; // 15 minutos
    private static final long REFRESH_TOKEN_VALIDITY = 1000 * 60 * 60 * 24; // 24 horas

    public String generateToken(UserDetails userDetails, String role, String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("role", "ROLE_" + role);

        SecretKey secretKey = Keys.hmacShaKeyFor(apiSecret.getBytes(StandardCharsets.UTF_8)); //Genera la clave
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY))
                .signWith(secretKey, SignatureAlgorithm.HS256) // Usamos la clave y el algoritmo
                .compact();
    }
}
