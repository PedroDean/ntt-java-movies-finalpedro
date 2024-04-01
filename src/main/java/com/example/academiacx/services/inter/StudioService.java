package com.example.academiacx.services.inter;

import com.example.academiacx.models.StudioModel;

import java.util.List;
import java.util.Optional;

public interface StudioService {

    List<StudioModel> list();

    Optional<StudioModel> findById(Long id);

    Optional<StudioModel> findByName(String name);

    StudioModel create(StudioModel studioModel);

    StudioModel update(StudioModel studioModel);

    boolean delete(Long id);
}
