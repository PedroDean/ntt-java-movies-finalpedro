package com.example.academiacx.facades.inter;

import com.example.academiacx.models.dto.UserBookmarkDto;
import com.example.academiacx.models.dto.UserDto;

public interface BookMarksFacade {

    UserBookmarkDto getFavorites(Long id);
}
