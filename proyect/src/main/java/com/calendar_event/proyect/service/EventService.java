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

    //Creamos un evento
    public EventModel createEvent(EventModel event, String userId) {
        event.setUser(new UserModel(userId));
        return eventRepository.save(event);
    }

    // Obtenemos todos los usuarios en un rango de fechas
    public List<EventModel> getEventsByUserId(String userId) {
        return eventRepository.findByIdAndUserId(userId);
    }

    // Obtenemos eventos de un usuario en un rango de fechas
    public List<EventModel> getEventsByDateRange(String userId, LocalDateTime start, LocalDateTime end) {
        return eventRepository.findByUserIdAndStartDateTimeBetween(userId, start, end);
    }

    // Actualizar un evento
    public EventModel updateEvent(Long eventId, EventModel updatedEvent, String userId) {
        EventModel existingEvent = eventRepository.findByIdAndUserId(eventId, userId);
        if (existingEvent == null) {
            throw new RuntimeException("Evento no encontrado");
        }
        existingEvent.setTitle(updatedEvent.getTitle());
        existingEvent.setDescription(updatedEvent.getDescription());
        existingEvent.setStartDateTime(updatedEvent.getStartDateTime());
        existingEvent.setEndDateTime(updatedEvent.getEndDateTime());
        existingEvent.setLocation(updatedEvent.getLocation());
        existingEvent.setEventType(updatedEvent.getEventType());
        return eventRepository.save(existingEvent);
    }

    // Eliminar un evento
    public void deleteEvent(Long eventId, String userId) {
        EventModel event = eventRepository.findByIdAndUserId(eventId, userId)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        eventRepository.delete(event);
    }

}
