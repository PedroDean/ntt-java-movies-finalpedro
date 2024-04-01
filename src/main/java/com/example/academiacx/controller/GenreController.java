package com.example.academiacx.controller;

import com.example.academiacx.models.GenreModel;
import com.example.academiacx.models.GenreModel;
import com.example.academiacx.models.StudioModel;
import com.example.academiacx.repository.GenreRepository;
import com.example.academiacx.services.inter.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(value = "/genre")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreModel>> findAll()
    {
        List<GenreModel> response = genreService.list();

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<GenreModel>> findById(@PathVariable Long id)
    {
        Optional<GenreModel> response = genreService.findById(id);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/save")
    public ResponseEntity<GenreModel> save(@RequestBody GenreModel genreModel)
    {
        GenreModel response = genreService.create(genreModel);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/update")
    public ResponseEntity<GenreModel> update(@RequestBody GenreModel genreModel)
    {
        GenreModel response = genreService.update(genreModel);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {

        Boolean success = genreService.delete(id);

        return success ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }
}
