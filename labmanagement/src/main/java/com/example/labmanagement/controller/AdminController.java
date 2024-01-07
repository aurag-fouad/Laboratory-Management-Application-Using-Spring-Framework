package com.example.labmanagement.controller;

import com.example.labmanagement.model.Project;
import com.example.labmanagement.model.Publication;
import com.example.labmanagement.model.Ressource;
import com.example.labmanagement.model.User;
import com.example.labmanagement.service.MembersService;
import com.example.labmanagement.service.ProjectsService;
import com.example.labmanagement.service.PublicationService;
import com.example.labmanagement.service.RessourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('admin')")
public class AdminController {
    private final MembersService membersService;
    private final ProjectsService projectsService;
    private final PublicationService publicationService;
    private final RessourcesService ressourcesService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(MembersService membersService, ProjectsService projectsService, PublicationService publicationService, RessourcesService ressourcesService, BCryptPasswordEncoder passwordEncoder) {
        this.membersService = membersService;
        this.projectsService = projectsService;
        this.publicationService = publicationService;
        this.ressourcesService = ressourcesService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/members")
    public String viewMembersList(Model model) {
        model.addAttribute("listMembers", membersService.getAllMembers());
        return "admin/members";
    }

    @GetMapping("/addNewMember")
    public String addNewMember(Model model) {
        User member = new User();
        model.addAttribute("member", member);
        return "admin/new_member";
    }

    @PostMapping("/saveMember")
    public String saveMember(@ModelAttribute("member") User member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        membersService.saveMember(member);
        return "redirect:/admin/members";
    }

    @GetMapping("/deleteMember/{id}")
    public String deleteMember(@PathVariable(value = "id") long id) {
        membersService.deleteMember(id);
        return "redirect:/admin/members";
    }

    @GetMapping("/updateMember/{id}")
    public String updateMember(@PathVariable(value = "id") long id, Model model) {

        User member = membersService.getMemberById(id);
        model.addAttribute("member", member);
        return "admin/update_member";
    }

    @GetMapping("/projects")
    public String viewProjectsList(Model model) {
        model.addAttribute("listProjects", projectsService.getAllProjects());
        return "admin/projects";
    }

    @GetMapping("/addNewProject")
    public String addNewProject(Model model) {
        Project project = new Project();
        model.addAttribute("project", project);
        return "admin/new_project";
    }

    @PostMapping("/saveProject")
    public String saveProject(@ModelAttribute("project") Project project) {
        projectsService.saveProject(project);
        return "redirect:/admin/projects";
    }

    @GetMapping("/deleteProject/{id}")
    public String deleteProject(@PathVariable(value = "id") long id) {
        projectsService.deleteProject(id);
        return "redirect:/admin/projects";
    }

    @GetMapping("/updateProject/{id}")
    public String updateProject(@PathVariable(value = "id") long id, Model model) {

        Project project = projectsService.getProjectById(id);
        model.addAttribute("project", project);
        return "admin/update_project";
    }

    @GetMapping("/pubs")
    public String viewPubsList(Model model) {
        model.addAttribute("listPubs", publicationService.getAllPubs());
        return "admin/pubs";
    }

    @GetMapping("/addNewPub")
    public String addNewPub(Model model) {
        Publication publication = new Publication();
        model.addAttribute("pub", publication);
        return "admin/new_pub";
    }

    @PostMapping("/savePub")
    public String savePub(@ModelAttribute("pub") Publication publication) {
        publicationService.savePub(publication);
        return "redirect:/admin/pubs";
    }

    @GetMapping("/deletePub/{id}")
    public String deletePub(@PathVariable(value = "id") long id) {
        publicationService.deletePub(id);
        return "redirect:/admin/pubs";
    }

    @GetMapping("/updatePub/{id}")
    public String updatePub(@PathVariable(value = "id") long id, Model model) {

        Publication publication = publicationService.getPubById(id);
        model.addAttribute("pub", publication);
        return "admin/update_pub";
    }

    @GetMapping("/ressources")
    public String viewRessourcesList(Model model) {
        model.addAttribute("listRessources", ressourcesService.getAllRessources());
        return "admin/ressource";
    }

    @GetMapping("/addNewRessource")
    public String addNewRessource(Model model) {
        Ressource ressource = new Ressource();
        model.addAttribute("ressource", ressource);
        return "admin/new_ressource";
    }

    @PostMapping("/saveRessource")
    public String saveRessource(@ModelAttribute("ressource") Ressource ressource) {
        ressourcesService.saveRessource(ressource);
        return "redirect:/admin/ressources";
    }

    @GetMapping("/deleteRessource/{id}")
    public String deleteRessource(@PathVariable(value = "id") long id) {
        ressourcesService.deleteRessource(id);
        return "redirect:/admin/ressources";
    }

    @GetMapping("/updateRessource/{id}")
    public String updateRessource(@PathVariable(value = "id") long id, Model model) {

        Ressource ressource = ressourcesService.getRessourceById(id);
        model.addAttribute("ressource", ressource);
        return "admin/update_ressource";
    }
}
