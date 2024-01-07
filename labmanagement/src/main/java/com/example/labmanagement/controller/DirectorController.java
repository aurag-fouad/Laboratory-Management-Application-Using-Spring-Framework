package com.example.labmanagement.controller;

import com.example.labmanagement.service.MembersService;
import com.example.labmanagement.service.ProjectsService;
import com.example.labmanagement.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/director")
@PreAuthorize("hasRole('director')")
public class DirectorController {
    private final MembersService membersService;
    private final ProjectsService projectsService;
    private final PublicationService publicationService;

    @Autowired
    public DirectorController(MembersService membersService, ProjectsService projectsService, PublicationService publicationService) {
        this.membersService = membersService;
        this.projectsService = projectsService;
        this.publicationService = publicationService;
    }

    @GetMapping("/members")
    public String viewMembersList(Model model) {
        model.addAttribute("listMembers", membersService.getAllMembers());
        return "director/members";
    }

    @GetMapping("/projects")
    public String viewProjectsList(Model model) {
        model.addAttribute("listProjects", projectsService.getAllProjects());
        return "director/projects";
    }

    @GetMapping("/pubs")
    public String viewPubsList(Model model) {
        model.addAttribute("listPubs", publicationService.getAllPubs());
        return "director/pubs";
    }
}
