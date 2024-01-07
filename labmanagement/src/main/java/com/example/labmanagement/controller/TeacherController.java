package com.example.labmanagement.controller;

import com.example.labmanagement.model.Publication;
import com.example.labmanagement.service.ProjectsService;
import com.example.labmanagement.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
@PreAuthorize("hasRole('teacher')")
public class TeacherController {

    private final ProjectsService projectsService;
    private final PublicationService publicationService;

    @Autowired
    public TeacherController(ProjectsService projectsService, PublicationService publicationService) {
        this.projectsService = projectsService;
        this.publicationService = publicationService;
    }

    @GetMapping("/projects")
    public String viewProjectsList(Model model) {
        model.addAttribute("listProjects", projectsService.getAllProjects());
        return "teacher/projects";
    }

    @GetMapping("/pubs")
    public String viewPubsList(Model model) {
        model.addAttribute("listPubs", publicationService.getAllPubs());
        return "teacher/pubs";
    }

    @GetMapping("/addNewPub")
    public String addNewPub(Model model) {
        Publication publication = new Publication();
        model.addAttribute("pub", publication);
        return "teacher/new_pub";
    }

    @PostMapping("/savePub")
    public String savePub(@ModelAttribute("pub") Publication publication) {
        publicationService.savePub(publication);
        return "redirect:/teacher/pubs";
    }
}
