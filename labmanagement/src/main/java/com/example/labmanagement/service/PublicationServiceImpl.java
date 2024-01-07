package com.example.labmanagement.service;

import com.example.labmanagement.model.Project;
import com.example.labmanagement.model.Publication;
import com.example.labmanagement.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;

    @Autowired
    public PublicationServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    @Override
    public List<Publication> getAllPubs() {
        return publicationRepository.findAll();
    }

    @Override
    public void savePub(Publication publication) {
        this.publicationRepository.save(publication);
    }

    @Override
    public void deletePub(Long id) {
        this.publicationRepository.deleteById(id);
    }

    @Override
    public Publication getPubById(long id) {
        Optional<Publication> optional = publicationRepository.findById(id);
        Publication publication = null;
        if(optional.isPresent()) {
            publication = optional.get();
        } else {
            throw new RuntimeException("Publication not found for id :: " +id);
        }
        return publication;
    }
}
