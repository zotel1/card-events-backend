package com.calendar_event.proyect.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    private String id;

    private String email; // aca podemos almacenar el email
    private String name; //Nombre de nuestro usuario

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> evenmmts = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvenmmts() {
        return evenmmts;
    }

    public void setEvenmmts(List<Event> evenmmts) {
        this.evenmmts = evenmmts;
    }
}
