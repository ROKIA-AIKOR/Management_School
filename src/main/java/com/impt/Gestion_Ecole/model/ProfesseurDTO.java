package com.impt.Gestion_Ecole.model;

import lombok.Data;


@Data
public class ProfesseurDTO {

    private String name;
    private String prenom;
    private String diplome;
    private String specialite;
    private String username;
    private String email;
    private String password;
    private User user;

}

/* Update Methode

Le problème dans votre méthode updateProfesseur du contrôleur réside dans le fait que vous utilisez deux annotations @RequestBody pour
 les paramètres Professeur et User. Une requête HTTP ne peut avoir qu'un seul corps (request body), donc vous ne pouvez pas utiliser
 @RequestBody deux fois pour des paramètres différents dans la même méthode.

Pour résoudre ce problème, vous pouvez créer une classe DTO (Data Transfer Object) regroupant
à la fois les informations du professeur et de l'utilisateur. Voici comment vous pouvez le faire :

    Créez une classe ProfesseurDTO avec les propriétés nécessaires pour mettre à jour à la fois le professeur et l'utilisateur :
 */
