package com.impt.Gestion_Ecole.services.services;

import com.impt.Gestion_Ecole.model.Etudiant;
import com.impt.Gestion_Ecole.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EtudiantService {
    public Etudiant createEtudiant(Etudiant etudiant, User user);

    public Etudiant updateEtudiant(Etudiant etudiant, User user);

    public Page<Etudiant> findPaginated(int pageNo, int pageSize);

    public Etudiant getEtudiantById(Long id);

    public List<Etudiant> getAllEtudiants();

    public void deleteEtudiant(Long id);
}
