package com.impt.Gestion_Ecole.services.ImpService;


import com.impt.Gestion_Ecole.model.Role;
import com.impt.Gestion_Ecole.model.TbConstants;
import com.impt.Gestion_Ecole.model.User;
import com.impt.Gestion_Ecole.repositories.RoleRepository;
import com.impt.Gestion_Ecole.repositories.UserRepository;
import com.impt.Gestion_Ecole.services.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
    public User updateUser(User user){
        // Vérifier si l'utilisateur existe
        User existingUser = userRepository.findById(user.getUser_id()).orElse(null);
        if (existingUser == null) {
            return null; // Ou lancez une exception appropriée si nécessaire
        }

        // Mettre à jour les propriétés de l'utilisateur
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());

        // Vérifier si le mot de passe a été modifié
        if (!user.getPassword().isEmpty()) {
            // Encoder le nouveau mot de passe
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            // Mettre à jour le mot de passe de l'utilisateur
            existingUser.setPassword(encodedPassword);
        }
        // Enregistrer les modifications dans la base de données
        return userRepository.save(existingUser);
    }



    @Override
    public void saveAdmin(User admins) {
        Role role = roleRepository.findByName(TbConstants.Roles.ADMIN);

        if (role == null)
            role = roleRepository.save(new Role(TbConstants.Roles.ADMIN));


        User admin = new User(
                admins.getUsername(),
                admins.getEmail(),
                passwordEncoder.encode(admins.getPassword()),
                Arrays.asList(role));
        userRepository.save(admin);
    }

    @Override
    public void saveEtudiante(User etudiantes) {
        Role role = roleRepository.findByName(TbConstants.Roles.ETUDIANTE);

        if (role == null)
            role = roleRepository.save(new Role(TbConstants.Roles.ETUDIANTE));


        User admin = new User(
                etudiantes.getUsername(),
                etudiantes.getEmail(),
                passwordEncoder.encode(etudiantes.getPassword()),
                Arrays.asList(role));
        userRepository.save(admin);
    }


    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
