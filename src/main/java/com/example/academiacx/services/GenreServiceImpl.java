package com.example.academiacx.services;

import com.example.academiacx.handlers.exceptions.ParametroInvalidoException;
import com.example.academiacx.handlers.exceptions.RecursoNaoEntradoException;
import com.example.academiacx.models.GenreModel;
import com.example.academiacx.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.academiacx.services.inter.GenreService;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    private GenreRepository genreRepository;
    @Override
    public List<GenreModel> list() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<GenreModel> findById(Long id) {
        return genreRepository.findById(id);
    }

    @Override
    public Optional<GenreModel> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public GenreModel create(GenreModel genreModel) {
        genreModel.setId(null);

        return genreRepository.save(genreModel);
    }

    @Override
    public GenreModel update(GenreModel genreModel) {
        if(genreModel.getId() == null || findById(genreModel.getId()).isEmpty()) {
            throw new ParametroInvalidoException("Id não encontrado");
        }

        return genreRepository.save(genreModel);
    }
    public boolean delete(Long id) {

        findById(id);

        try {
            genreRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RecursoNaoEntradoException("Id informado não encontrado!");
        }

    }


}
