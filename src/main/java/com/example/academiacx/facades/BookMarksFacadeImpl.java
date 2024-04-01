package com.example.academiacx.facades;

import com.example.academiacx.facades.inter.BookMarksFacade;
import com.example.academiacx.models.DirectorModel;
import com.example.academiacx.models.MovieModel;
import com.example.academiacx.models.UserModel;
import com.example.academiacx.models.dto.UserBookmarkDto;
import com.example.academiacx.models.dto.UserDto;
import com.example.academiacx.services.inter.MovieService;
import com.example.academiacx.services.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookMarksFacadeImpl implements BookMarksFacade {

    @Autowired
    MovieService movieService;

    @Autowired
    UserService userService;

    @Override
    public UserBookmarkDto getFavorites(Long id) {

        UserBookmarkDto userBookmarkDto = new UserBookmarkDto();

        Optional<UserModel> userModel = userService.findById(id);

        List<MovieModel> movieFavorites = movieService.getUser(userModel.get().getId());

        userBookmarkDto.setUserDto(new UserDto(userModel.get()));
        userBookmarkDto.setMovies(movieFavorites);
        userBookmarkDto.setDirectors(new ArrayList<DirectorModel>());

        return userBookmarkDto;
    }
}
