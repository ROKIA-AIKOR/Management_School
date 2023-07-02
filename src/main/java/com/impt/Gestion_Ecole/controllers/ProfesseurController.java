package com.impt.Gestion_Ecole.controllers;

import com.impt.Gestion_Ecole.model.Professeur;
import com.impt.Gestion_Ecole.model.ProfesseurDTO;
import com.impt.Gestion_Ecole.model.Role;
import com.impt.Gestion_Ecole.model.User;
import com.impt.Gestion_Ecole.services.services.ProfesseurService;
import com.impt.Gestion_Ecole.services.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProfesseurController {
    private ProfesseurService professeurService;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public ProfesseurController(ProfesseurService professeurService, UserService userService, PasswordEncoder passwordEncoder) {
        this.professeurService = professeurService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    /*******************************
     *
     * Affiche la liste de professeur
     *
     *******************************/

    @GetMapping("/professeur-list")
    public String getAllProfesseurs(Model model) {
        List<Professeur> professeurs = professeurService.getAllProfesseurs();
        model.addAttribute("professeurs", professeurs);
        return "professeur/professeur-list";
    }


    /*******************************
     *
     * Affiche la liste de professeur pagination
     *
     *******************************/
    @GetMapping("/pagination-professeurs")
    public String showProfesseurList(Model model,
                                     @RequestParam(defaultValue = "0") int pageNo,
                                     @RequestParam(defaultValue = "2") int pageSize) {
        Page<Professeur> professeurPage = professeurService.findPaginated(pageNo, pageSize);
        model.addAttribute("professeurPage", professeurPage);
        return "professeur/professeur-list";
    }

    /*******************************
     *
     * affiche par id professeur
     *
     *******************************/
    @GetMapping("/detail-professeurs/{id}")
    public String getProfesseurById(@PathVariable("id") Long id, Model model) {
        Professeur professeur = professeurService.getProfesseurById(id);
        model.addAttribute("professeur", professeur);
        return "professeur/professeur-details";
    }


    /*******************************
     *
     * Ajouter professeur
     *
     *******************************/

    @GetMapping("/professeur-form")
    public String showAddProfesseurForm(Model model) {
        Professeur professeur = new Professeur();
        model.addAttribute("professeur", professeur);
        return "/professeur/professeur-form";
    }

    @PostMapping("/professeur-form")
    public String addProfesseur(@ModelAttribute("professeur") Professeur professeur,
                                @RequestParam("username") String username,
                                @RequestParam("email") String email,
                                @RequestParam("password") String password) {
        User user = new User(username, email, passwordEncoder.encode(password),
                new ArrayList<>(Arrays.asList(new Role("ROLE_PROFESSEUR"))));
        professeurService.createProfesseur(professeur, user);
        //return "redirect:/professeur-form?success";
        return "redirect:/professeur-list";

    }




    /*******************************
     *
     * Modifier  professeur
     *
     *******************************/

    @GetMapping("/professeurs/edit/{id}")
    public String showUpdateProfesseurForm(@PathVariable("id") Long id, Model model) {
        Professeur professeur = professeurService.getProfesseurById(id);
        model.addAttribute("professeur", professeur);
        return "professeur/professeur-modification";
    }

    @PostMapping("/professeurs/edit/{id}")
    public String updateProfesseur(@PathVariable("id") Long id, @ModelAttribute ProfesseurDTO professeurDTO) {
        // Vérifier si le professeur existe
        Professeur existingProfesseur = professeurService.getProfesseurById(id);
        if (existingProfesseur == null) {
            return "redirect:/professeur-list";
        }

        // Mettre à jour les propriétés du professeur
        existingProfesseur.setName(professeurDTO.getName());
        existingProfesseur.setPrenom(professeurDTO.getPrenom());
        existingProfesseur.setDiplome(professeurDTO.getDiplome());
        existingProfesseur.setSpecialite(professeurDTO.getSpecialite());

        // Mettre à jour l'utilisateur associé
        User existingUser = existingProfesseur.getUser();
        existingUser.setUsername(professeurDTO.getUser().getUsername());
        existingUser.setEmail(professeurDTO.getUser().getEmail());
        existingUser.setPassword(professeurDTO.getUser().getPassword());

        // Enregistrer les modifications dans la base de données
        Professeur updatedProfesseur = professeurService.updateProfesseur(existingProfesseur, existingUser);

        return "redirect:/professeur-list";
    }

    /*******************************
     *
     * Supprimer professeur
     *
     *******************************/

    @GetMapping("/professeurs/{id}")
    public String deleteStudent(@PathVariable Long id) {
        professeurService.deleteProfesseur(id);
        return "redirect:/professeur-list";
    }


}
