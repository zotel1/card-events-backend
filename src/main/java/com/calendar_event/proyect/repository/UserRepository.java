package com.calendar_event.proyect.repository;

import com.calendar_event.proyect.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
}
