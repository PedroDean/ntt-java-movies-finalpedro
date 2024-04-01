package com.example.academiacx.services.inter;

import com.example.academiacx.models.DirectorModel;

import java.util.List;
import java.util.Optional;

public interface DirectorService {
    List<DirectorModel> list();

    Optional<DirectorModel> findById(Long id);

    DirectorModel create(DirectorModel directorModel);

    DirectorModel update(DirectorModel directorModel);

    Optional<DirectorModel> findByName(String name);

    Boolean delete(Long id);
}
