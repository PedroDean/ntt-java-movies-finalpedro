package com.example.academiacx.controller;

import com.example.academiacx.models.DirectorModel;
import com.example.academiacx.services.inter.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/directors")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @GetMapping
    public ResponseEntity<List<DirectorModel>> findAll() {
        List<DirectorModel> response = directorService.list();
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<DirectorModel>> findById(@PathVariable Long id) {
        Optional<DirectorModel> response = directorService.findById(id);
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<Optional<DirectorModel>> findByName(@PathVariable String name) {
        Optional<DirectorModel> response = directorService.findByName(name);
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @PostMapping(value = "save")
    public ResponseEntity<DirectorModel> save(@RequestBody DirectorModel directorModel) {
        DirectorModel response = directorService.create(directorModel);
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "update")
    public ResponseEntity<DirectorModel> update(@PathVariable Long id, @RequestBody DirectorModel directorModel) {
        DirectorModel response = directorService.update(directorModel);
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        Boolean success = directorService.delete(id);
        return success ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }
}