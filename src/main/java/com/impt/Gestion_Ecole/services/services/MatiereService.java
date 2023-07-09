package com.impt.Gestion_Ecole.services.services;


import com.impt.Gestion_Ecole.model.Matiere;
import com.impt.Gestion_Ecole.model.Professeur;

import java.util.List;

public interface MatiereService {

    Matiere createMatiere(Matiere matiere, Professeur professeur);

    Matiere getMatiereById(Long id);

    List<Matiere> getAllMatieres();

    List<Matiere> getMatieresByProfesseur(Professeur professeur);

    Matiere updateMatiere(Matiere matiere);

    void deleteMatiere(Long id);

}
