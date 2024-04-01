package com.example.academiacx.services.inter;

import com.example.academiacx.models.StreamingModel;
import com.example.academiacx.models.StudioModel;

import java.util.List;
import java.util.Optional;

public interface StreamingService {

    List<StreamingModel> list();

    Optional<StreamingModel> findById(Long id);

    StreamingModel create(StreamingModel streamingModel);

    StreamingModel update(StreamingModel streamingModel);

    Boolean delete(Long id);
}
