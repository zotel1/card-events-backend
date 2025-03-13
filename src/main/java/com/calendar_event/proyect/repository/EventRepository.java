package com.calendar_event.proyect.repository;

import com.calendar_event.proyect.model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventModel, Long> {

    // Encuantra todos los eventos del usuario
    Optional<EventModel> findByIdAndUserId(Long id, String userId);

    List<EventModel> findByUserId(String userId);




    // Encuentra eventos de un usuario en un rango de fechas
    List<EventModel> findByUserIdAndStartDateTimeBetween(String userId, LocalDateTime start, LocalDateTime end);

    // Encuentra un evento por su ID y el ID del usuario ( para asegurar que el usuario solo acceda a sus eventos
   // EventModel findByIdAndUserId(Long id, String userId);
}
