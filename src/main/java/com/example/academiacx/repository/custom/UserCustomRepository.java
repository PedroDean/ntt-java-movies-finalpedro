package com.example.academiacx.repository.custom;

import com.example.academiacx.models.UserModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<UserModel> customQuery(String name, String email)
    {
        StringBuilder query = new StringBuilder("from UserModel u where u.name = :name");

        if (email != null)
        {
            query.append(" and u.email = :email");
        }

        TypedQuery<UserModel> typedQuery = entityManager.createQuery(query.toString(), UserModel.class);
        typedQuery.setParameter("name", name);

        if (email != null)
        {
            typedQuery.setParameter("email", email);
        }

        return typedQuery.getResultList();
    }
}
