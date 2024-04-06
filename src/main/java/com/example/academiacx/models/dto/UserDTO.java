package com.example.academiacx.models.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private Long id;
    private String name;
    private String email;

    private String username;
    private List<String> favoriteMovies;
    private List<String> favoriteDirectors;


    public UserDTO() {}

    public UserDTO(Long id, String name, String email, List<String> favoriteMovies, List<String> favoriteDirectors) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.favoriteMovies = favoriteMovies;
        this.favoriteDirectors = favoriteDirectors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<String> getFavoriteMovies() {
        return favoriteMovies;
    }
    public void setFavoriteMovies(List<String> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }
    public List<String> getFavoriteDirectors() {
        return favoriteDirectors;
    }
    public void setFavoriteDirectors(List<String> favoriteDirectors) {
        this.favoriteDirectors = favoriteDirectors;
    }
    public String getUsername() {
        return username;
    }
}
