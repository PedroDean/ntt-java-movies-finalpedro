package com.example.academiacx.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_movie")
@JsonPropertyOrder({"id", "title", "genre", "studio", "franchise", "streaming", "directors", "actors"})
public class MovieModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O Titulo é obrigatório")
    private String title;

    @NotNull(message = "O Genero é Obrigatório")
    @Valid
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreModel genre;

    @NotNull(message = "O Estudio deve ser informado")
    @JsonIgnore
    @Valid
    @ManyToOne
    @JoinColumn(name = "studio_id")
    private StudioModel studio;

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    @JsonIgnore
    private FranchiseModel franchise;

    @NotNull
    @JsonIgnore
    @Valid
    @ManyToMany(mappedBy = "movies")
    private List<DirectorModel> directors;

    @ManyToOne
    @JsonManagedReference
    @Valid
    @JoinColumn(name = "streaming_id")
    private StreamingModel streaming;

    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    @JsonIgnore
    private List<ActorModel> actors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @JsonIgnore
    public FranchiseModel getFranchise() {
        return franchise;
    }

    public void setFranchise(FranchiseModel franchise) {
        this.franchise = franchise;
    }

    public List<DirectorModel> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorModel> directors) {
        this.directors = directors;
    }

    @JsonProperty("directors")
    public List<String> getDirectorsNames() {
        List<String> directorNames = new ArrayList<>();
        if (directors != null) {
            for (DirectorModel director : directors) {
                directorNames.add(director.getName());
            }
        }
        return directorNames;
    }

    @JsonProperty("franchise")
    public String getFranchiseTitle() {
        if (franchise != null) {
            return franchise.getName();
        }
        return null;
    }

    @JsonProperty("streaming")
    public String getStreamingDetails() {
        if (streaming != null) {
            return "Name: " + streaming.getName() + ", URL: " + streaming.getUrl();
        }
        return null;
    }

    public StreamingModel getStreaming() {
        return streaming;
    }

    public void setStreaming(StreamingModel streaming) {
        this.streaming = streaming;
    }

    @JsonProperty("actors")
    public List<String> getActorsNames() {
        List<String> actorNames = new ArrayList<>();
        if (actors != null) {
            for (ActorModel actor : actors) {
                actorNames.add(actor.getName());
            }
        }
        return actorNames;
    }

    public List<ActorModel> getActors() {
        return actors;
    }

    public void setActors(List<ActorModel> actors) {
        this.actors = actors;
    }
}
