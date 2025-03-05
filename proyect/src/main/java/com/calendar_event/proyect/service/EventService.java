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
        return eventRepository.findByUserId(userId);
    }

    // Obtenemos eventos de un usuario en un rango de fechas
    public List<EventModel> getEventsByDateRange(String userId, LocalDateTime start, LocalDateTime end) {
        return eventRepository.findByUserIdAndStartDateTimeBetween(userId, start, end);
    }

}
