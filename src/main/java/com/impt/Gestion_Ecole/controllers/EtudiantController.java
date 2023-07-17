package com.impt.Gestion_Ecole.controllers;

import com.impt.Gestion_Ecole.dto.EtudiantDTO;
import com.impt.Gestion_Ecole.model.Etudiant;
import com.impt.Gestion_Ecole.model.User;
import com.impt.Gestion_Ecole.services.services.EtudiantService;
import com.impt.Gestion_Ecole.services.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.impt.Gestion_Ecole.model.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class EtudiantController {
    private EtudiantService etudiantService;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public EtudiantController(EtudiantService etudiantService, UserService userService, PasswordEncoder passwordEncoder) {
        this.etudiantService = etudiantService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    /*******************************
     *
     * Affiche la liste des étudiants
     *
     *******************************/
    @GetMapping("/etudiant-list")
    public String getAllEtudiants(Model model) {
        List<Etudiant> etudiants = etudiantService.getAllEtudiants();
        model.addAttribute("etudiants", etudiants);
        return "etudiant/etudiant-list";
    }

    /*******************************
     *
     * Affiche la liste des étudiants avec pagination
     *
     *******************************/
    @GetMapping("/pagination-etudiants")
    public String showEtudiantList(Model model,
                                   @RequestParam(defaultValue = "0") int pageNo,
                                   @RequestParam(defaultValue = "2") int pageSize) {
        Page<Etudiant> etudiantPage = etudiantService.findPaginated(pageNo, pageSize);
        model.addAttribute("etudiantPage", etudiantPage);
        return "etudiant/etudiant-list";
    }

    /*******************************
     *
     * Affiche les détails d'un étudiant par son ID
     *
     *******************************/
    @GetMapping("/detail-etudiants/{id}")
    public String getEtudiantById(@PathVariable("id") Long id, Model model) {
        Etudiant etudiant = etudiantService.getEtudiantById(id);
        model.addAttribute("etudiant", etudiant);
        return "etudiant/etudiant-details";
    }

    /*******************************
     *
     * Ajoute un nouvel étudiant
     *
     *******************************/
    @GetMapping("/etudiant-form")
    public String showAddEtudiantForm(Model model) {
        Etudiant etudiant = new Etudiant();
        model.addAttribute("etudiant", etudiant);
        return "/etudiant/etudiant-form";
    }

    @PostMapping("/etudiant-form")
    public String addEtudiant(@ModelAttribute("etudiant") Etudiant etudiant,
                              @RequestParam("username") String username,
                              @RequestParam("email") String email,
                              @RequestParam("password") String password) {
        User user = new User(username, email, passwordEncoder.encode(password),
                new ArrayList<>(Arrays.asList(new Role("ROLE_ETUDIANTE"))));

        etudiant.setUser(user);
        etudiantService.createEtudiant(etudiant, user);
        return "redirect:/etudiant-list";
    }



    /*******************************
     *
     * Modifie les informations d'un étudiant
     *
     *******************************/
    @GetMapping("/etudiants/edit/{id}")
    public String showUpdateEtudiantForm(@PathVariable("id") Long id, Model model) {
        Etudiant etudiant = etudiantService.getEtudiantById(id);
        model.addAttribute("etudiant", etudiant);
        return "etudiant/etudiant-modification";
    }

    @PostMapping("/etudiants/edit/{id}")
    public String updateEtudiant(@PathVariable("id") Long id, @ModelAttribute EtudiantDTO etudiantDTO) {
        // Vérifier si l'étudiant existe
        Etudiant existingEtudiant = etudiantService.getEtudiantById(id);
        if (existingEtudiant == null) {
            return "redirect:/etudiant-list";
        }

        // Mettre à jour les propriétés de l'étudiant
        existingEtudiant.setName(etudiantDTO.getName());
        existingEtudiant.setPrenom(etudiantDTO.getPrenom());
        existingEtudiant.setAdresse(etudiantDTO.getAdresse());
        existingEtudiant.setDate_naissance(etudiantDTO.getDate_naissance());
        existingEtudiant.setNiveau(etudiantDTO.getNiveau());
        existingEtudiant.setSpecialite(etudiantDTO.getSpecialite());

        // Mettre à jour l'utilisateur associé
        User existingUser = existingEtudiant.getUser();
        existingUser.setUsername(etudiantDTO.getUser().getUsername());
        existingUser.setEmail(etudiantDTO.getUser().getEmail());
        existingUser.setPassword(etudiantDTO.getUser().getPassword());

        // Enregistrer les modifications dans la base de données
        etudiantService.updateEtudiant(existingEtudiant, existingUser);

        return "redirect:/etudiant-list";
    }

    /*******************************
     *
     * Supprime un étudiant
     *
     *******************************/
    @GetMapping("/etudiants/delete/{id}")
    public String deleteEtudiant(@PathVariable("id") Long id) {
        etudiantService.deleteEtudiant(id);
        return "redirect:/etudiant-list";
    }

}

