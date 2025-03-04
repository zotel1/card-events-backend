package com.calendar_event.proyect.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class EventModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDateTime starDateTime;
    private LocalDateTime endDateTime;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user; //Lo relacionamos con el usuario

    public EventModel() {}

    public EventModel(Long id, String title, String description, LocalDateTime starDateTime, LocalDateTime endDateTime) {
        this.title = title;
        this.description = description;
        this.starDateTime = starDateTime;
        this.endDateTime= endDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStarDateTime() {
        return starDateTime;
    }

    public void setStarDateTime(LocalDateTime starDateTime) {
        this.starDateTime = starDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
}
