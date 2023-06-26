package com.impt.Gestion_Ecole.controllers;

import com.impt.Gestion_Ecole.services.UserService;
import com.impt.Gestion_Ecole.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    public String loginForm() {
        return "login";
    }

    //Ajouter Etudiante
    @GetMapping("/registration_etudiante")
    public String registrationFormEtudiante(Model model) {
        User etudiante = new User();
        model.addAttribute("etudiante", etudiante);
       return "registration_etudiante";
      // return "redirect:/";
    }

    @PostMapping("/registration_etudiante")
    public String registrationEtudiante(
            @Valid @ModelAttribute("etudiante") User etudiantes,
            BindingResult result,
            Model model) {
        User existingUser = userService.findUserByEmail(etudiantes.getEmail());

        if (existingUser != null)
            result.rejectValue("email", null,
                    "User already registered !!!");

        if (result.hasErrors()) {
            model.addAttribute("etudiante", etudiantes);
            return "/registration_etudiante";
        }

        userService.saveEtudiante(etudiantes);
        return "redirect:/registration_etudiante?success";

    }

    //Ajouter professeur
    @GetMapping("/registration_prof")
    public String registrationFormProf(Model model) {
        User prof = new User();
        model.addAttribute("prof", prof);
        return "registration_prof";
        // return "redirect:/";
    }

    @PostMapping("/registration_prof")
    public String registrationProf(
            @Valid @ModelAttribute("prof") User profs,
            BindingResult result,
            Model model) {
        User existingUser = userService.findUserByEmail(profs.getEmail());

        if (existingUser != null)
            result.rejectValue("email", null,
                    "User already registered !!!");

        if (result.hasErrors()) {
            model.addAttribute("prof", profs);
            return "/registration_prof";
        }

        userService.saveProf(profs);
        return "redirect:/registration_prof?success";

    }
    //Ajouter  Admin
    @GetMapping("/registration_admin")
    public String registrationFormAdmin(Model model) {
        User admin = new User();
        model.addAttribute("admin", admin);
        return "registration_admin";
        // return "redirect:/";
    }
    @PostMapping("/registration_admin")
    public String registrationAdmin(
            @Valid @ModelAttribute("admin") User admins,
            BindingResult result,
            Model model) {
        User existingUser = userService.findUserByEmail(admins.getEmail());

        if (existingUser != null)
            result.rejectValue("email", null,
                    "User already registered !!!");

        if (result.hasErrors()) {
            model.addAttribute("admin", admins);
            return "/registration_admin";
        }

        userService.saveAdmin(admins);
        return "redirect:/registration_admin?success";

    }

}
