package com.example.academiacx.services;

import com.example.academiacx.handlers.exceptions.ParametroInvalidoException;
import com.example.academiacx.handlers.exceptions.RecursoNaoEntradoException;
import com.example.academiacx.models.ActorModel;
import com.example.academiacx.repository.ActorRepository;
import com.example.academiacx.services.inter.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Override
    public List<ActorModel> list() {
        return actorRepository.findAll();
    }

    @Override
    public Optional<ActorModel> findById(Long id) {
        return actorRepository.findById(id);
    }

    @Override
    public Optional<ActorModel> findByName(String name) {
        return actorRepository.findByName(name);
    }

    @Override
    public ActorModel create(ActorModel actorModel) {
        actorModel.setId(null);
        return actorRepository.save(actorModel);
    }

    @Override
    public ActorModel update(ActorModel actorModel) {
        if (actorModel.getId() == null || !actorRepository.existsById(actorModel.getId())) {
            throw new ParametroInvalidoException("Id não encontrado");
        }
        return actorRepository.save(actorModel);
    }

    @Override
    public boolean delete(Long id) {
        if (actorRepository.existsById(id)) {
            actorRepository.deleteById(id);
            return true;
        } else {
            throw new RecursoNaoEntradoException("Id informado não encontrado!");
        }
    }
}