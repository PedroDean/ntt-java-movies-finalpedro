package com.example.academiacx.services;

import com.example.academiacx.handlers.exceptions.ParametroInvalidoException;
import com.example.academiacx.handlers.exceptions.RecursoNaoEntradoException;
import com.example.academiacx.models.MovieModel;
import com.example.academiacx.repository.MovieRepository;
import com.example.academiacx.services.inter.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<MovieModel> listMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<MovieModel> findById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public MovieModel create(MovieModel movieModel) {
        movieModel.setId(null);
        return movieRepository.save(movieModel);
    }

    @Override
    public MovieModel update(MovieModel movieModel) {
        if (movieModel.getId() == null || findById(movieModel.getId()).isEmpty()) {
            throw new ParametroInvalidoException("Id não encontrado");
        }
        return movieRepository.save(movieModel);
    }


    @Override
    public boolean delete(Long id) {
        findById(id);
        try {
            movieRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RecursoNaoEntradoException("Id informado não encontrado!");
        }
    }

    @Override
    public List<MovieModel> getUser(Long id) {
            return movieRepository.findAll();
    }
}