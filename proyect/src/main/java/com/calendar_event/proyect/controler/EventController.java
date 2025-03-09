package com.calendar_event.proyect.controler;

import com.calendar_event.proyect.model.EventModel;
import com.calendar_event.proyect.model.UserModel;
import com.calendar_event.proyect.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<EventModel> createEvent(@RequestBody EventModel event, @AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject(); // Obtiene el user_id del token JWT
        event.setUser(new UserModel(userId)); // Asocia el evento al usuario
        EventModel savedEvent = eventService.save(event);
        return ResponseEntity.ok(savedEvent);
    }

    @GetMapping
    public ResponseEntity<List<EventModel>> getEvents(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        List<EventModel> events = eventService.findByUserId(userId);
        return ResponseEntity.ok(events);
    }
}
