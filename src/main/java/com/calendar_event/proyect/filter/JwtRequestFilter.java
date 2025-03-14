package com.calendar_event.proyect.filter;

import com.calendar_event.proyect.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

@Autowired
    JwtTokenService jwtTokenService;
}
