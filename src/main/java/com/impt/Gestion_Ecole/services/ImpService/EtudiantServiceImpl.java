package com.impt.Gestion_Ecole.services.ImpService;

import com.impt.Gestion_Ecole.model.Etudiant;
import com.impt.Gestion_Ecole.model.User;
import com.impt.Gestion_Ecole.repositories.EtudiantRepository;
import com.impt.Gestion_Ecole.repositories.RoleRepository;
import com.impt.Gestion_Ecole.repositories.UserRepository;
import com.impt.Gestion_Ecole.services.services.EtudiantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantServiceImpl implements EtudiantService {

    private EtudiantRepository etudiantRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public EtudiantServiceImpl(EtudiantRepository etudiantRepository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.etudiantRepository = etudiantRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Page<Etudiant> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return etudiantRepository.findAll(pageable);
    }

    public Etudiant createEtudiant(Etudiant etudiant, User user) {
        User savedUser = userRepository.save(user);
        etudiant.setUser(savedUser);
        return etudiantRepository.save(etudiant);
    }

    public Etudiant updateEtudiant(Etudiant etudiant, User user) {
        User savedUser = userRepository.save(user);
        etudiant.setUser(savedUser);
        return etudiantRepository.save(etudiant);
    }

    public Etudiant getEtudiantById(Long id) {
        return etudiantRepository.findById(id).orElse(null);
    }

    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    public void deleteEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }
}
