package com.example.academiacx.repository;

import com.example.academiacx.models.StreamingModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreamingRepository extends JpaRepository<StreamingModel, Long> {

    StreamingModel findByName(String name);
}
