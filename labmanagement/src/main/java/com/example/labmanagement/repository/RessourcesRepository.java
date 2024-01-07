package com.example.labmanagement.repository;

import com.example.labmanagement.model.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RessourcesRepository extends JpaRepository<Ressource, Long> {
}
