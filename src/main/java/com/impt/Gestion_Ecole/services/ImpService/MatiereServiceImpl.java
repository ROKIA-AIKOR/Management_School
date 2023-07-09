package com.impt.Gestion_Ecole.services.ImpService;


import com.impt.Gestion_Ecole.model.Matiere;
import com.impt.Gestion_Ecole.model.Professeur;

import com.impt.Gestion_Ecole.repositories.MatiereRepository;
import com.impt.Gestion_Ecole.services.services.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatiereServiceImpl implements MatiereService {

    private final MatiereRepository matiereRepository;

    @Autowired
    public MatiereServiceImpl(MatiereRepository matiereRepository) {
        this.matiereRepository = matiereRepository;
    }

    @Override
    public Matiere createMatiere(Matiere matiere, Professeur professeur) {
        matiere.setProfesseur(professeur);
        return matiereRepository.save(matiere);
    }

    @Override
    public Matiere getMatiereById(Long id) {
        return matiereRepository.findById(id).orElse(null);
    }

    @Override
    public List<Matiere> getAllMatieres() {
        return matiereRepository.findAll();
    }

    @Override
    public List<Matiere> getMatieresByProfesseur(Professeur professeur) {
        return matiereRepository.findAllByProfesseur(professeur);
    }

    @Override
    public Matiere updateMatiere(Matiere matiere) {
        return matiereRepository.save(matiere);
    }

    @Override
    public void deleteMatiere(Long id) {
        matiereRepository.deleteById(id);
    }
}

