package com.calendar_event.proyect.service;

import com.calendar_event.proyect.model.EventModel;
import com.calendar_event.proyect.model.UserModel;
import com.calendar_event.proyect.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    //Creamos un evento
    public EventModel createEvent(EventModel event, String userId) {
        event.setUser(new UserModel(userId));
        return eventRepository.save(event);
    }

}
