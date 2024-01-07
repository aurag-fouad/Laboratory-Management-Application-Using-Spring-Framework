package com.example.labmanagement.service;

import com.example.labmanagement.model.Publication;

import java.util.List;

public interface PublicationService {
    List<Publication> getAllPubs();
    void savePub(Publication publication);
    void deletePub(Long id);
    Publication getPubById(long id);
}
