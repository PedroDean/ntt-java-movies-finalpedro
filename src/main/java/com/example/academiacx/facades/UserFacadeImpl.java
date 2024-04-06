package com.example.academiacx.facades;

import com.example.academiacx.facades.inter.UserFacade;
import com.example.academiacx.models.DirectorModel;
import com.example.academiacx.models.MovieModel;
import com.example.academiacx.models.UserModel;
import com.example.academiacx.models.dto.UserDTO;
import com.example.academiacx.services.inter.DirectorService;
import com.example.academiacx.services.inter.MovieService;
import com.example.academiacx.services.inter.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MovieService movieService;

    @Autowired
    private DirectorService directorService;;


    @Override
    public List<UserDTO> listUsers() {
        List<UserModel> users = userService.listUsers();
        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        Optional<UserModel> user = userService.findById(id);
        return user.map(this::convertToDto);
    }

    @Override
    public UserDTO create(UserDTO userDTO) {
        UserModel userModel = convertToEntity(userDTO);
        UserModel createdUser = userService.create(userModel);
        return convertToDto(createdUser);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        UserModel userModel = convertToEntity(userDTO);
        UserModel updatedUser = userService.update(userModel);
        return convertToDto(updatedUser);
    }

    @Override
    public boolean deleteById(Long id) {
        return userService.delete(id);
    }

    @Override
    public ResponseEntity<?> addFavoriteMovie(Long userId, Long movieId) {
        Optional<UserModel> optionalUser = userService.findById(userId);
        Optional<MovieModel> optionalMovie = movieService.findById(movieId);

        if (optionalUser.isPresent() && optionalMovie.isPresent()) {
            UserModel user = optionalUser.get();
            MovieModel movie = optionalMovie.get();

            user.addFavoriteMovieTitle(movie.getTitle());
            userService.update(user);

            return ResponseEntity.ok("Favorite movie added successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> addFavoriteDirector(Long userId, Long directorId) {
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
    private UserDTO convertToDto(UserModel userModel) {
        return modelMapper.map(userModel, UserDTO.class);
    }

    private UserModel convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserModel.class);
    }
}
