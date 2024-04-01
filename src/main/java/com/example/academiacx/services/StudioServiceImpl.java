package com.example.academiacx.services;

import com.example.academiacx.handlers.exceptions.ParametroInvalidoException;
import com.example.academiacx.handlers.exceptions.RecursoNaoEntradoException;
import com.example.academiacx.models.StudioModel;
import com.example.academiacx.repository.StudioRepository;
import com.example.academiacx.repository.custom.StudioCustomRepository;
import com.example.academiacx.services.inter.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudioServiceImpl implements StudioService {

    @Autowired
    private StudioRepository studioRepository;

    @Override
    public List<StudioModel> list() {
        return studioRepository.findAll();
    }

    @Override
    public Optional<StudioModel> findById(Long id) {

        return studioRepository.findById(id);
    }

    @Override
    public Optional<StudioModel> findByName(String name) {

        return Optional.ofNullable(studioRepository.findByName(name));
    }

    @Override
    public StudioModel create(StudioModel studioModel) {
        studioModel.setId(null);

        return studioRepository.save(studioModel);
    }

    @Override
    public StudioModel update(StudioModel studioModel) {
        if(studioModel.getId() == null || findById(studioModel.getId()).isEmpty()) {
            throw new ParametroInvalidoException("Id não encontrado");
        }

        return studioRepository.save(studioModel);
    }

    @Override
    public boolean delete(Long id) {
        findById(id);

        try {
            studioRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RecursoNaoEntradoException("Id informado não encontrado!");
        }
    }
}