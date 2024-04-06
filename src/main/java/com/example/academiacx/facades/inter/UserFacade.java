package com.example.academiacx.facades.inter;

import com.example.academiacx.models.UserModel;
import com.example.academiacx.models.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserFacade {
    List<UserDTO> listUsers();

    Optional<UserDTO> findById(Long id);


    UserDTO create(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    boolean deleteById(Long id);
    ResponseEntity<?> addFavoriteMovie(Long userId, Long movieId);

    ResponseEntity<?> addFavoriteDirector(Long userId, Long directorId);
}
