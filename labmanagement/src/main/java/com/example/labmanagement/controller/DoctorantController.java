package com.example.labmanagement.controller;

import com.example.labmanagement.service.ProjectsService;
import com.example.labmanagement.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctorant")
@PreAuthorize("hasRole('doctorant')")
public class DoctorantController {

    private final ProjectsService projectsService;
    private final PublicationService publicationService;

    @Autowired
    public DoctorantController(ProjectsService projectsService, PublicationService publicationService) {
        this.projectsService = projectsService;
        this.publicationService = publicationService;
    }

    @GetMapping("/projects")
    public String viewProjectsList(Model model) {
        model.addAttribute("listProjects", projectsService.getAllProjects());
        return "doctorant/projects";
    }

    @GetMapping("/pubs")
    public String viewPubsList(Model model) {
        model.addAttribute("listPubs", publicationService.getAllPubs());
        return "doctorant/pubs";
    }
}
