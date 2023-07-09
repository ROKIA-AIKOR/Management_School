package com.impt.Gestion_Ecole.repositories;

import com.impt.Gestion_Ecole.model.Matiere;
import com.impt.Gestion_Ecole.model.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Long> {

    // Ajoutez ici des méthodes spécifiques si nécessaire

    @Query("SELECT m FROM Matiere m WHERE m.professeur = :professeur")
    List<Matiere> findAllByProfesseur(Professeur professeur);

}
