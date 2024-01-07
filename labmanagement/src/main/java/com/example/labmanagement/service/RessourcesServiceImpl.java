package com.example.labmanagement.service;

import com.example.labmanagement.model.Ressource;
import com.example.labmanagement.repository.RessourcesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RessourcesServiceImpl implements RessourcesService{

    private final RessourcesRepository ressourcesRepository;

    @Autowired
    public RessourcesServiceImpl(RessourcesRepository ressourcesRepository) {
        this.ressourcesRepository = ressourcesRepository;
    }

    @Override
    public List<Ressource> getAllRessources() {
        return ressourcesRepository.findAll();
    }

    @Override
    public void saveRessource(Ressource ressource) {
        this.ressourcesRepository.save(ressource);
    }

    @Override
    public void deleteRessource(Long id) {
        this.ressourcesRepository.deleteById(id);
    }

    @Override
    public Ressource getRessourceById(long id) {
        Optional<Ressource> optional = ressourcesRepository.findById(id);
        Ressource ressource = null;
        if(optional.isPresent()) {
            ressource = optional.get();
        } else {
            throw new RuntimeException("Publication not found for id :: " +id);
        }
        return ressource;
    }
}
