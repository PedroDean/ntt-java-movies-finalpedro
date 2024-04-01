package com.example.academiacx.services;

import com.example.academiacx.models.DirectorModel;
import com.example.academiacx.repository.DirectorRepository;
import com.example.academiacx.services.inter.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public List<DirectorModel> list() {
        return directorRepository.findAll();
    }

    @Override
    public Optional<DirectorModel> findById(Long id) {
        return directorRepository.findById(id);
    }

    @Override
    public DirectorModel create(DirectorModel directorModel) {
        return directorRepository.save(directorModel);
    }

    @Override
    public DirectorModel update(DirectorModel directorModel) {
        return directorRepository.save(directorModel);
    }

    @Override
    public Optional<DirectorModel> findByName(String name) {
        return directorRepository.findByName(name);
    }

    @Override
    public Boolean delete(Long id) {
        if (directorRepository.existsById(id)) {
            directorRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}