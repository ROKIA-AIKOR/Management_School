package com.impt.Gestion_Ecole.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.impt.Gestion_Ecole.model.Matiere;
import com.impt.Gestion_Ecole.model.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class EtudiantDTO {
    private Long etudiant_id;

    private String name;
    private String prenom;
    private String adresse;
    private String date_naissance;
    private String niveau;
    private String specialite;

    private User user;
    private List<Matiere> matieres;
}
