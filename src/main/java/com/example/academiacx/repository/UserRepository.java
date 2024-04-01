package com.example.academiacx.repository;

import com.example.academiacx.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByName (String name);

    UserModel findByEmail (String email);

    UserModel findByEmailAndName(String email, String name);

    @Query("from UserModel u where u.name = :name") //JPQL
    UserModel busquePorNome (String name);

    @Query("from UserModel u where u.email = :email") //JPQL
    UserModel busquePorEmail (@Param("email") String email);


    UserModel findByUsername(String username);

}
