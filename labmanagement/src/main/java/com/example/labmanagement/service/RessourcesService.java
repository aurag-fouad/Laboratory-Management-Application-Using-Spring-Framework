package com.example.labmanagement.service;

import com.example.labmanagement.model.Ressource;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RessourcesService {
    List<Ressource> getAllRessources();
    void saveRessource(Ressource ressource);
    void deleteRessource(Long id);
    Ressource getRessourceById(long id);
}
