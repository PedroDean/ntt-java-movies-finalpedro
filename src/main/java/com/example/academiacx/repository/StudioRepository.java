package com.example.academiacx.repository;

import com.example.academiacx.models.StreamingModel;
import com.example.academiacx.models.StudioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudioRepository extends JpaRepository<StudioModel, Long> {

    StudioModel findByName(String name);

    StudioModel findByCountry(String country);

    StudioModel findByNameOrCountry(String name, String country);

    //JPQL
    @Query("from StudioModel sm where sm.name = :name")
    StudioModel buscaPorNome(String name);

    @Query("from StudioModel sm where sm.country = :country")
    StudioModel buscaPorPais(String country);
}
