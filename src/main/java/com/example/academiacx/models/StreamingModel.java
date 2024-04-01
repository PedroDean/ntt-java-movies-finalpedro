package com.example.academiacx.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_streaming")
public class StreamingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O Nome do Streaming é obrigatório")
    private String name;

    @NotBlank(message = "A Url do Streaming é obrigatória")
    private String url;

    @JsonBackReference
    @OneToMany(mappedBy = "streaming", fetch = FetchType.EAGER)
    private List<MovieModel> movies;

    @Transient
    public List<String> getMovieTitles() {
        List<String> movieTitles = new ArrayList<>();
        for (MovieModel movie : movies) {
            movieTitles.add(movie.getTitle());
        }
        return movieTitles;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MovieModel> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieModel> movies) {
        this.movies = movies;
    }
}
