package com.example.academiacx.repository;

import com.example.academiacx.models.GenreModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreModel, Long> {
}
