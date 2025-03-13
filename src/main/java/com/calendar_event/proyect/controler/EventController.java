package com.calendar_event.proyect.controler;

import com.calendar_event.proyect.model.EventModel;
import com.calendar_event.proyect.model.UserModel;
import com.calendar_event.proyect.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // Crear un evento
    @PostMapping
    public ResponseEntity<EventModel> createEvent(@RequestBody EventModel event, @AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        EventModel savedEvent = eventService.createEvent(event, userId);
        return ResponseEntity.ok(savedEvent);
    }

    // Obtener todos los eventos de un usuario
    @GetMapping
    public ResponseEntity<List<EventModel>> getEvents(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        List<EventModel> events = eventService.getEventsByUserId(userId);
        return ResponseEntity.ok(events);
    }

    // Obtener eventos en un rango de fechas
    @GetMapping("/range")
    public ResponseEntity<List<EventModel>> getEventsByDateRange(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
            @AuthenticationPrincipal Jwt jwt) {

        String userId = jwt.getSubject();
        List<EventModel> events = eventService.getEventsByDateRange(userId, start, end);
        return ResponseEntity.ok(events);
    }

    // Actualizamos un evento
    @PutMapping("/{eventId}")
    public ResponseEntity<EventModel> updateEvent(
            @PathVariable Long eventId,
            @RequestBody EventModel updatedEvent,
            @AuthenticationPrincipal Jwt jwt) {

        String userId = jwt.getSubject();
        EventModel event = eventService.updateEvent(eventId, updatedEvent, userId);
        return ResponseEntity.ok(event);
    }

    // Eliminar un evento
    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(
            @PathVariable Long eventId,
            @AuthenticationPrincipal Jwt jwt) {

        String userId = jwt.getSubject();
        eventService.deleteEvent(eventId, userId);
        return ResponseEntity.noContent().build();
    }
}
