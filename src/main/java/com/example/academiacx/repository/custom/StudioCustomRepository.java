package com.example.academiacx.repository.custom;

import com.example.academiacx.models.StudioModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudioCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<StudioModel> customQuery(String nome, String pais)
    {
        StringBuilder query = new StringBuilder("from StudioModel sm where sm.name = :name");

        if (pais != null)
        {
            query.append(" and sm.country = :country");
        }

        TypedQuery<StudioModel> typedQuery = entityManager.createQuery(query.toString(), StudioModel.class);
        typedQuery.setParameter("name", nome);

        if (pais != null)
        {
            typedQuery.setParameter("country", pais);
        }

        return typedQuery.getResultList();
    }
}
