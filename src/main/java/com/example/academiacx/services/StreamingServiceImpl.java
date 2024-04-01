package com.example.academiacx.services;

import com.example.academiacx.handlers.exceptions.ParametroInvalidoException;
import com.example.academiacx.handlers.exceptions.RecursoNaoEntradoException;
import com.example.academiacx.models.StreamingModel;
import com.example.academiacx.repository.StreamingRepository;
import com.example.academiacx.services.inter.StreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StreamingServiceImpl implements StreamingService {

    @Autowired
    private StreamingRepository streamingRepository;

    @Override
    public List<StreamingModel> list() {
        return streamingRepository.findAll();
    }

    @Override
    public Optional<StreamingModel> findById(Long id) {
        return streamingRepository.findById(id);
    }

    @Override
    public StreamingModel create(StreamingModel streamingModel) {
        streamingModel.setId(null);
        return streamingRepository.save(streamingModel);
    }

    @Override
    public StreamingModel update(StreamingModel streamingModel) {
        if (streamingModel.getId() == null || !streamingRepository.existsById(streamingModel.getId())) {
            throw new ParametroInvalidoException("ID não encontrado");
        }
        return streamingRepository.save(streamingModel);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<StreamingModel> existingStreaming = findById(id);
        if (existingStreaming.isPresent()) {
            streamingRepository.deleteById(id);
            return true;
        } else {
            throw new RecursoNaoEntradoException("O streaming com o ID fornecido não foi encontrado.");
        }
    }
}