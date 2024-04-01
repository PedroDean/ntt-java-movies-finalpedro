package com.example.academiacx.services.inter;

import com.example.academiacx.models.GenreModel;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    List<GenreModel> list();

    Optional<GenreModel> findById(Long id);

    Optional<GenreModel> findByName(String name);

    GenreModel create(GenreModel genreModel);

    GenreModel update(GenreModel genreModel);

    boolean delete(Long id);
}
