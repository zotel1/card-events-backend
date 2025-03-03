package com.calendar_event.proyect.controler;

import com.auth0.jwt.JWT;
import com.calendar_event.proyect.model.Event;
import com.calendar_event.proyect.model.User;
import io.jsonwebtoken.Jwt;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private  EventService eventService;

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event, @AuthenticationPrincipal JWT jwt) {
        String userId = jwt.getSubject(); // Obtiene el user_id del token JWT
        event.setUser(new User(userId)); // Asocia el evento al usuario
        Event savedEvent = eventService.save(event);
        return ResponseEntity.ok(savedEvent);
    }

    @GetMapping
    public ResponseEntity<List<Event>> getEvents(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        List<Event> events = eventService.findByUserId(userId);
        return ResponseEntity.ok(events);
    }
}
