package com.impt.Gestion_Ecole.repositories;

import com.impt.Gestion_Ecole.model.Professeur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesseurRepository extends JpaRepository<Professeur,Long> {

}
