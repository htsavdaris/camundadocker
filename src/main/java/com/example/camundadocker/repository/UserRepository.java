package com.example.camundadocker.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.example.camundadocker.model.cam_user;

@Component
public interface UserRepository extends CrudRepository<cam_user, Long> {
    
    Optional<cam_user> findByLogin(String login);
    
  }
