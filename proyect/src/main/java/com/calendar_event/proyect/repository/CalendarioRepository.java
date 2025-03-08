package com.calendar_event.proyect.repository;

import com.calendar_event.proyect.model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarioRepository extends JpaRepository<EventModel, Long> {
}
