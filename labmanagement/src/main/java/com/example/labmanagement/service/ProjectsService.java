package com.example.labmanagement.service;
import java.util.List;

import com.example.labmanagement.model.Project;

public interface ProjectsService{
    List<Project> getAllProjects();
    void saveProject(Project project);
    void deleteProject(Long id);
    Project getProjectById(long id);
}
