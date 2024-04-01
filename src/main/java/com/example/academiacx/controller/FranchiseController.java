package com.example.academiacx.controller;

import com.example.academiacx.models.FranchiseModel;
import com.example.academiacx.services.inter.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/franchise")
public class FranchiseController {

    @Autowired
    private FranchiseService franchiseService;

    @GetMapping
    public ResponseEntity<List<FranchiseModel>> findAll()
    {
        List<FranchiseModel> response = franchiseService.list();

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<FranchiseModel>> findById(@PathVariable Long id)
    {
        Optional<FranchiseModel> response = franchiseService.findById(id);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<Optional<FranchiseModel>> findByName(@PathVariable String name)
    {
        Optional<FranchiseModel> response = franchiseService.findByName(name);
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/save")
    public ResponseEntity<FranchiseModel> save(@RequestBody FranchiseModel franchiseModel)
    {
        FranchiseModel response = franchiseService.create(franchiseModel);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/update")
    public ResponseEntity<FranchiseModel> update(@PathVariable Long id, @RequestBody FranchiseModel franchiseModel)
    {
        FranchiseModel response = franchiseService.update(franchiseModel);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value ="delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {

        Boolean success = franchiseService.delete(id);

        return success ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }
}