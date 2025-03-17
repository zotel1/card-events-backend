package com.calendar_event.proyect.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private String id;

    @Column(name = "email", nullable = false)
    private String email; // aca podemos almacenar el email

    @Column(name = "name", nullable = false)
    private String name; //Nombre de nuestro usuario

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventModel> evenmmts = new ArrayList<>();

    public UserModel() {}

    public UserModel(String id, String email, String name, List<EventModel> events, String password, String role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.evenmmts = events;
        this.password = password;
        this.role = role;
    }

    public UserModel(String userId) {

    }

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

    public List<EventModel> getEvenmmts() {
        return evenmmts;
    }

    public void setEvenmmts(List<EventModel> evenmmts) {
        this.evenmmts = evenmmts;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
