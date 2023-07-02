package com.impt.Gestion_Ecole.services.services;

import com.impt.Gestion_Ecole.model.Professeur;
import com.impt.Gestion_Ecole.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProfesseurService {
    public Professeur createProfesseur(Professeur professeur, User user);

    public Professeur updateProfesseur(Professeur professeur, User user);
    public Page<Professeur> findPaginated(int pageNo, int pageSize);

    public Professeur getProfesseurById(Long id);

    public List<Professeur> getAllProfesseurs();

    public void deleteProfesseur(Long id);
}
