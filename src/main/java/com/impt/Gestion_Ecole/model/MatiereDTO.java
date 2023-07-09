package com.impt.Gestion_Ecole.model;


import lombok.Data;

@Data
public class MatiereDTO {
    private Long matiereId;
    private String nom;
    private String description;
    private ProfesseurDTO professeur;

}
