package com.example.academiacx.services.inter;

import com.example.academiacx.models.UserModel;
import com.example.academiacx.models.dto.UserBookmarkDto;
import com.example.academiacx.models.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserModel> listUsers();

    Optional<UserModel> findById(Long id);

    UserModel create(UserModel userModel);

    UserModel update(UserModel userModel);

    boolean delete(Long id);
}
