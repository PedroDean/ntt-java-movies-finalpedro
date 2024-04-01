package com.example.academiacx.repository;

import com.example.academiacx.models.DirectorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DirectorRepository extends JpaRepository<DirectorModel, Long> {
    Optional<DirectorModel> findByName(String name);
}
