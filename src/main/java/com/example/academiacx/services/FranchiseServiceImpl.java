package com.example.academiacx.services;

import com.example.academiacx.handlers.exceptions.ParametroInvalidoException;
import com.example.academiacx.handlers.exceptions.RecursoNaoEntradoException;
import com.example.academiacx.models.FranchiseModel;
import com.example.academiacx.repository.FranchiseRepository;
import com.example.academiacx.services.inter.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FranchiseServiceImpl implements FranchiseService {

    @Autowired
    private FranchiseRepository franchiseRepository;

    @Override
    public List<FranchiseModel> list() {
        return franchiseRepository.findAll();
    }

    @Override
    public Optional<FranchiseModel> findById(Long id) {
        return franchiseRepository.findById(id);
    }

    @Override
    public FranchiseModel create(FranchiseModel franchiseModel) {
        franchiseModel.setId(null);
        return franchiseRepository.save(franchiseModel);
    }

    @Override
    public FranchiseModel update(FranchiseModel franchiseModel) {
        if (franchiseModel.getId() == null || findById(franchiseModel.getId()).isEmpty()) {
            throw new ParametroInvalidoException("Id não encontrado");
        }
        return franchiseRepository.save(franchiseModel);
    }

    @Override
    public Optional<FranchiseModel> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Boolean delete(Long id) {
        findById(id);
        try {
            franchiseRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RecursoNaoEntradoException("Id informado não encontrado!");
        }
    }

    @Override
    public List<FranchiseModel> getUser(Long id) {
        return franchiseRepository.findAll();
    }
}