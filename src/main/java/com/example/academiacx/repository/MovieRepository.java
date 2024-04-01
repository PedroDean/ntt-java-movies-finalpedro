package com.example.academiacx.repository;

import com.example.academiacx.models.MovieModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<MovieModel, Long> {


}
