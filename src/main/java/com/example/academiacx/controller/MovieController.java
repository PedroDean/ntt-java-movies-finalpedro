package com.example.academiacx.controller;

import com.example.academiacx.models.*;
import com.example.academiacx.services.inter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private StreamingService streamingService;

    @Autowired
    private FranchiseService franchiseService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private DirectorService directorService;

    @GetMapping
    public ResponseEntity<List<MovieModel>> findAll() {
        List<MovieModel> response = movieService.listMovies();
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<MovieModel>> findById(@PathVariable Long id) {
        Optional<MovieModel> response = movieService.findById(id);
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/save")
    public ResponseEntity<MovieModel> save(@RequestBody MovieModel movieModel) {
        MovieModel response = movieService.create(movieModel);
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{movieId}/add-streaming/{streamingId}")
    public ResponseEntity<MovieModel> adicionarStreamingAoFilme(@PathVariable Long movieId, @PathVariable Long streamingId) {
        Optional<MovieModel> optionalMovie = movieService.findById(movieId);
        if (optionalMovie.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<StreamingModel> optionalStreaming = streamingService.findById(streamingId);
        if (optionalStreaming.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        MovieModel movie = optionalMovie.get();
        StreamingModel streaming = optionalStreaming.get();

        movie.setStreaming(streaming);
        movieService.update(movie);

        return ResponseEntity.ok(movie);
    }

    @PutMapping("/{movieId}/add-franchise/{franchiseId}")
    public ResponseEntity<MovieModel> associarFilmeAFranquia(@PathVariable Long movieId, @PathVariable Long franchiseId) {
        Optional<MovieModel> optionalMovie = movieService.findById(movieId);
        if (optionalMovie.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<FranchiseModel> optionalFranchise = franchiseService.findById(franchiseId);
        if (optionalFranchise.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        MovieModel movie = optionalMovie.get();
        FranchiseModel franchise = optionalFranchise.get();

        movie.setFranchise(franchise);
        movieService.update(movie);

        List<MovieModel> franchiseMovies = franchise.getMovies();
        if (franchiseMovies == null) {
            franchiseMovies = new ArrayList<>();
        }
        franchiseMovies.add(movie);
        franchise.setMovies(franchiseMovies);
        franchiseService.update(franchise);

        return ResponseEntity.ok(movie);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<MovieModel> update(@RequestBody MovieModel movieModel) {
        MovieModel response = movieService.update(movieModel);
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{movieId}/add-actor/{actorId}")
    public ResponseEntity<MovieModel> adicionarAtorAoFilme(@PathVariable Long movieId, @PathVariable Long actorId) {
        Optional<MovieModel> optionalMovie = movieService.findById(movieId);
        if (optionalMovie.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<ActorModel> optionalActor = actorService.findById(actorId);
        if (optionalActor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        MovieModel movie = optionalMovie.get();
        ActorModel actor = optionalActor.get();

        List<ActorModel> actors = movie.getActors();
        if (actors == null) {
            actors = new ArrayList<>();
        }
        actors.add(actor);
        movie.setActors(actors);
        movieService.update(movie);


        List<MovieModel> movies = actor.getMovies();
        if (movies == null) {
            movies = new ArrayList<>();
        }
        movies.add(movie);
        actor.setMovies(movies);
        actorService.update(actor);

        return ResponseEntity.ok(movie);
    }
    @PutMapping ("/{movieId}/add-director/{directorId}")
    public ResponseEntity<MovieModel> adicionarDiretorAoFilme(@PathVariable Long movieId, @PathVariable Long directorId) {
        Optional<MovieModel> optionalMovie = movieService.findById(movieId);
        Optional<DirectorModel> optionalDirector = directorService.findById(directorId);

        if (optionalMovie.isEmpty() || optionalDirector.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        MovieModel movie = optionalMovie.get();
        DirectorModel director = optionalDirector.get();

        List<DirectorModel> directors = movie.getDirectors();
        if (directors == null) {
            directors = new ArrayList<>();
        }
        directors.add(director);
        movie.setDirectors(directors);
        movieService.update(movie);

        List<MovieModel> directorMovies = director.getMovies();
        if (directorMovies == null) {
            directorMovies = new ArrayList<>();
        }
        directorMovies.add(movie);
        director.setMovies(directorMovies);
        directorService.update(director);

        return ResponseEntity.ok(movie);
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        Boolean success = movieService.delete(id);
        return success ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("delete/streaming/{movieId}")
    public ResponseEntity<?> removeStreamingFromMovie(@PathVariable Long movieId) {
        Optional<MovieModel> optionalMovie = movieService.findById(movieId);

        if (optionalMovie.isPresent()) {
            MovieModel movie = optionalMovie.get();
            StreamingModel streaming = movie.getStreaming();

            if (streaming != null) {
                movie.setStreaming(null);

                streaming.getMovies().remove(movie);

                movieService.update(movie);

                return ResponseEntity.ok("Streaming removed from movie successfully.");
            } else {
                return ResponseEntity.badRequest().body("Movie does not have a streaming.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}