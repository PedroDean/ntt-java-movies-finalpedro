package com.example.academiacx.controller;

import com.example.academiacx.facades.inter.BookMarksFacade;
import com.example.academiacx.models.DirectorModel;
import com.example.academiacx.models.MovieModel;
import com.example.academiacx.models.UserModel;
import com.example.academiacx.models.dto.UserBookmarkDto;
import com.example.academiacx.repository.UserRepository;
import com.example.academiacx.services.inter.DirectorService;
import com.example.academiacx.services.inter.MovieService;
import com.example.academiacx.services.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookMarksFacade bookMarksFacade;

    @Autowired
    private MovieService movieService;
    @Autowired
    private DirectorService directorService;

    @GetMapping(value = "/list")
    public ResponseEntity<List<UserModel>> findAll()
    {
        List<UserModel> response = userService.listUsers();

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<UserModel>> findById(@PathVariable Long id)
    {
        Optional<UserModel> response = userService.findById(id);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/bookmark/{id}")
    public ResponseEntity<?> findFavoritesBookMark(@PathVariable Long id)
    {
        UserBookmarkDto response = bookMarksFacade.getFavorites(id);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/create")
    public ResponseEntity<UserModel> save(@RequestBody UserModel userModel)
    {
        UserModel response = userService.create(userModel);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }
    @PutMapping("/{userId}/add-favorite-movie/{movieId}")
    public ResponseEntity<?> addFavoriteMovieToUser(@PathVariable Long userId, @PathVariable Long movieId) {
        Optional<UserModel> optionalUser = userService.findById(userId);
        Optional<MovieModel> optionalMovie = movieService.findById(movieId);

        if (optionalUser.isPresent() && optionalMovie.isPresent()) {
            UserModel user = optionalUser.get();
            MovieModel movie = optionalMovie.get();

            List<String> favoriteMovies = user.getFavoritesMovies();
            user.addFavoriteMovieTitle(movie.getTitle());
            userService.update(user);

            return ResponseEntity.ok("Favorite movie added successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{userId}/add-favorite-director/{directorId}")
    public ResponseEntity<?> addFavoriteDirectorToUser(@PathVariable Long userId, @PathVariable Long directorId) {
        Optional<UserModel> optionalUser = userService.findById(userId);
        Optional<DirectorModel> optionalDirector = directorService.findById(directorId);

        if (optionalUser.isPresent() && optionalDirector.isPresent()) {
            UserModel user = optionalUser.get();
            DirectorModel director = optionalDirector.get();

            user.addFavoriteDirectorName(director.getName());
            userService.update(user);

            return ResponseEntity.ok("Favorite director added successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<UserModel> update(@RequestBody UserModel userModel)
    {
        UserModel response = userService.update(userModel);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {

        Boolean success = userService.delete(id);

        return success ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }
}
