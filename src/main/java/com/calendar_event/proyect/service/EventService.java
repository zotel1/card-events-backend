package com.calendar_event.proyect.service;

import com.calendar_event.proyect.model.EventModel;
import com.calendar_event.proyect.model.UserModel;
import com.calendar_event.proyect.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    // Crear un evento
    public EventModel createEvent(EventModel event, String userId) {
        event.setUser(new UserModel(userId)); // Asocia el evento al usuario
        return eventRepository.save(event);
    }

    // Obtener todos los eventos de un usuario
    public List<EventModel> getEventsByUserId(String userId) {
        return eventRepository.findByUserId(userId);
    }

    // Obtener eventos de un usuario en un rango de fechas
    public List<EventModel> getEventsByDateRange(String userId, LocalDateTime start, LocalDateTime end) {
        return eventRepository.findByUserIdAndStartDateTimeBetween(userId, start, end);
    }

    // Actualizar un evento
    public EventModel updateEvent(Long eventId, EventModel updatedEvent, String userId) {
        EventModel existingEvent = eventRepository.findByIdAndUserId(eventId, userId)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        // Actualiza solo los campos que fueron modificados
        if (updatedEvent.getTitle() != null) {
            existingEvent.setTitle(updatedEvent.getTitle());
        }
        if (updatedEvent.getDescription() != null) {
            existingEvent.setDescription(updatedEvent.getDescription());
        }
        if (updatedEvent.getStartDateTime() != null) {
            existingEvent.setStartDateTime(updatedEvent.getStartDateTime());
        }
        if (updatedEvent.getEndDateTime() != null) {
            existingEvent.setEndDateTime(updatedEvent.getEndDateTime());
        }

        return eventRepository.save(existingEvent);
    }

    // Eliminar un evento
    public void deleteEvent(Long eventId, String userId) {
        EventModel event = eventRepository.findByIdAndUserId(eventId, userId)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        eventRepository.delete(event);
    }
}
