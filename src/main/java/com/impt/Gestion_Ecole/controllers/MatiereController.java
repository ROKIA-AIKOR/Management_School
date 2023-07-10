package com.impt.Gestion_Ecole.controllers;



import com.impt.Gestion_Ecole.model.*;

import com.impt.Gestion_Ecole.services.services.MatiereService;
import com.impt.Gestion_Ecole.services.services.ProfesseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class MatiereController {

    private final MatiereService matiereService;
    private final ProfesseurService professeurService;

    @Autowired
    public MatiereController(MatiereService matiereService, ProfesseurService professeurService) {
        this.matiereService = matiereService;
        this.professeurService = professeurService;
    }


    /*******************************
     *
     * Ajouter Matiere
     *
     *******************************/


    @GetMapping("/matiere-form")
    public String showAddMatiereForm(Model model) {
        Matiere matiere = new Matiere();
        List<Professeur> professeurs = professeurService.getAllProfesseurs();
        model.addAttribute("matiere", matiere);
        model.addAttribute("professeurs", professeurs);
        model.addAttribute("professeur", new Professeur()); // Ajout de cette ligne
        return "/matiere/matiere-form";
    }



    @PostMapping("/matiere-form")
    public String addMatiere(@ModelAttribute("matiere") Matiere matiere,
                             @RequestParam("professeurId") Long professeurId) {
        Professeur professeur = professeurService.getProfesseurById(professeurId);
        matiereService.createMatiere(matiere, professeur);
         return "redirect:/matiere-list";

    }

    /*******************************
     *
     * Affiche la liste de matier
     *
     *******************************/
    @GetMapping("/matiere-list")
    public String getAllMatieres(Model model) {
        List<Matiere> matieres = matiereService.getAllMatieres();
        model.addAttribute("matieres", matieres);
        return "matiere/matiere-list";
    }

    /*******************************
     *
     * affiche par id matier
     *
     *******************************/
    @GetMapping("/matiere/{id}")
    public String getMatiereById(@PathVariable("id") Long matiereId, Model model) {
        Matiere matiere = matiereService.getMatiereById(matiereId);
        model.addAttribute("matiere", matiere);
        return "matiere/matiere-details";
    }

    /*******************************
     *
     * Supprimer matiere
     *
     *******************************/

    @GetMapping("/matiere-delete/{id}")
    public String deleteMatiere(@PathVariable Long id) {
        matiereService.deleteMatiere(id);
        return "redirect:/matiere-list";
    }

    /*******************************
     *
     * Modifier matière
     *
     *******************************/

    @GetMapping("/matieres/edit/{id}")
    public String showUpdateMatiereForm(@PathVariable("id") Long id, Model model) {
        Matiere matiere = matiereService.getMatiereById(id);
        List<Professeur> professeurs = professeurService.getAllProfesseurs();
        model.addAttribute("matiere", matiere);
        model.addAttribute("professeurs", professeurs);
        return "matiere/matiere-modification";
    }

    @PostMapping("/matieres/edit/{id}")
    public String updateMatiere(@PathVariable("id") Long id, @ModelAttribute("matiere") Matiere matiere) {
        // Récupérer la matière existante
        Matiere existingMatiere = matiereService.getMatiereById(id);
        if (existingMatiere == null) {
            return "redirect:/matiere-list";
        }

        // Mettre à jour les propriétés de la matière
        existingMatiere.setNom(matiere.getNom());
        existingMatiere.setDescription(matiere.getDescription());

        // Récupérer le professeur sélectionné
        Professeur professeur = professeurService.getProfesseurById(matiere.getProfesseur().getProf_id());
        existingMatiere.setProfesseur(professeur);

        // Enregistrer les modifications dans la base de données
        matiereService.updateMatiere(existingMatiere);

        return "redirect:/matiere-list";
    }



}
