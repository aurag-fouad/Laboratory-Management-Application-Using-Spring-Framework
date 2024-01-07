package com.example.labmanagement.service;

import com.example.labmanagement.model.Project;
import com.example.labmanagement.repository.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectsServiceImpl implements ProjectsService{

    private final ProjectsRepository projectsRepository;

    @Autowired
    public ProjectsServiceImpl(ProjectsRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }

    @Override
    public List<Project> getAllProjects() {
        return projectsRepository.findAll();
    }

    @Override
    public void saveProject(Project project) {
        this.projectsRepository.save(project);
    }

    @Override
    public void deleteProject(Long id) {
        this.projectsRepository.deleteById(id);
    }

    @Override
    public Project getProjectById(long id) {
        Optional<Project> optional = projectsRepository.findById(id);
        Project project = null;
        if(optional.isPresent()) {
            project = optional.get();
        } else {
            throw new RuntimeException("Employeenot not found for id :: " +id);
        }
        return project;
    }
}
