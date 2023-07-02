package com.impt.Gestion_Ecole.services.ImpService;

import com.impt.Gestion_Ecole.model.Professeur;
import com.impt.Gestion_Ecole.model.User;
import com.impt.Gestion_Ecole.repositories.ProfesseurRepository;
import com.impt.Gestion_Ecole.repositories.RoleRepository;
import com.impt.Gestion_Ecole.repositories.UserRepository;
import com.impt.Gestion_Ecole.services.services.ProfesseurService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesseurServiceImpl implements ProfesseurService {


    private ProfesseurRepository professeurRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public ProfesseurServiceImpl(ProfesseurRepository professeurRepository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.professeurRepository = professeurRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Page<Professeur> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return professeurRepository.findAll(pageable);
    }

    public Professeur createProfesseur(Professeur professeur, User user) {
        User savedUser = userRepository.save(user);
        professeur.setUser(savedUser);
        return professeurRepository.save(professeur);
    }

    public Professeur updateProfesseur(Professeur professeur, User user) {
        User savedUser = userRepository.save(user);
        professeur.setUser(savedUser);
        return professeurRepository.save(professeur);
    }




    public Professeur getProfesseurById(Long id) {
        return professeurRepository.findById(id).orElse(null);
    }

    public List<Professeur> getAllProfesseurs() {
        return professeurRepository.findAll();
    }

    public void deleteProfesseur(Long id) {
        professeurRepository.deleteById(id);
    }

}
