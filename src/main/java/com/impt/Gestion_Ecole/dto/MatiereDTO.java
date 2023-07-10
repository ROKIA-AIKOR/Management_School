package com.impt.Gestion_Ecole.dto;


import lombok.Data;

@Data
public class MatiereDTO {
    private Long matiereId;
    private String nom;
    private String description;
    private ProfesseurDTO professeur;

}
