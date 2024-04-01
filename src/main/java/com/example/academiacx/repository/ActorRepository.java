package com.example.academiacx.repository;

import com.example.academiacx.models.ActorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActorRepository extends JpaRepository<ActorModel, Long> {
    Optional<ActorModel> findByName(String name);
}
