package com.example.academiacx.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_franchise")
public class FranchiseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da franquia é obrigatória")
    private String name;

    @NotNull(message = "O Genero é obrigatório")
    @Valid
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreModel genre;

    @NotNull(message = "O Studio é obrigatório")
    @JsonIgnore
    @Valid
    @ManyToOne
    @JoinColumn(name = "studio_id")
    private StudioModel studio;

    @OneToMany(mappedBy = "franchise")
    @JsonIgnore
    private List<MovieModel> movies;

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

    public GenreModel getGenre() {
        return genre;
    }

    public void setGenre(GenreModel genre) {
        this.genre = genre;
    }

    public StudioModel getStudio() {
        return studio;
    }

    public void setStudio(StudioModel studio) {
        this.studio = studio;
    }

    public List<MovieModel> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieModel> movies) {
        this.movies = movies;
    }

    public List<String> getMovieTitles() {
        List<String> movieTitles = new ArrayList<>();
        if (movies != null) {
            for (MovieModel movie : movies) {
                movieTitles.add(movie.getTitle());
            }
        }
        return movieTitles;
    }
}
