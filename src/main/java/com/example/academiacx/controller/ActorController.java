package com.example.academiacx.controller;

import com.example.academiacx.models.ActorModel;
import com.example.academiacx.services.inter.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping
    public ResponseEntity<List<ActorModel>> findAll()
    {
        List<ActorModel> response = actorService.list();

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<ActorModel>> findById(@PathVariable Long id)
    {
        Optional<ActorModel> response = actorService.findById(id);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<Optional<ActorModel>> findByName(@PathVariable String name)
    {
        Optional<ActorModel> response = actorService.findByName(name);
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ActorModel> save(@RequestBody ActorModel actorModel)
    {
        ActorModel response = actorService.create(actorModel);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "update")
    public ResponseEntity<ActorModel> update(@PathVariable Long id, @RequestBody ActorModel actorModel)
    {
        ActorModel response = actorService.update(actorModel);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value ="delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {

        Boolean success = actorService.delete(id);

        return success ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }
}