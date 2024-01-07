package com.example.labmanagement.controller;

import com.example.labmanagement.model.User;
import com.example.labmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

@Controller
public class UserDashboardController {

    private final UserService userService;

    @Autowired
    public UserDashboardController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String viewDashboard(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_admin"))) {
                return "redirect:/admin";
            } else if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_teacher"))) {
                return "redirect:/teacher";
            } else if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_doctorant"))) {
                return "redirect:/doctorant";
            } else if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_director"))) {
                return "redirect:/director";
            }
        }
        // Default redirect for unauthenticated or unknown roles
        return "redirect:/login"; // You can change this to an appropriate landing page
    }

    @GetMapping("/admin")
    public String adminPage(@AuthenticationPrincipal User user, Model model) {

        return "admin/index";
    }

    @GetMapping("/director")
    public String directorPage(@AuthenticationPrincipal User user, Model model) {

        return "director/index";
    }

    @GetMapping("/teacher")
    public String teacherPage(@AuthenticationPrincipal User user, Model model) {

        return "teacher/index";
    }

    @GetMapping("/doctorant")
    public String doctorantPage(@AuthenticationPrincipal User user, Model model) {

        return "doctorant/index";
    }

    @GetMapping("/error")
    public String getErrorPage() {
        return "redirect:/";
    }

    @PostMapping("/error")
    public String postErrorPage() {
        return "redirect:/";
    }

}
