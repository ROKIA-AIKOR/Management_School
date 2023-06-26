package com.impt.Gestion_Ecole.services;


import com.impt.Gestion_Ecole.model.Role;
import com.impt.Gestion_Ecole.model.TbConstants;
import com.impt.Gestion_Ecole.model.User;
import com.impt.Gestion_Ecole.repositories.RoleRepository;
import com.impt.Gestion_Ecole.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void saveProf(User profs) {
        Role role = roleRepository.findByName(TbConstants.Roles.PROFESSEUR);

        if (role == null)
            role = roleRepository.save(new Role(TbConstants.Roles.PROFESSEUR));


        User user = new User(
                profs.getEmail(),
                passwordEncoder.encode(profs.getPassword()),
                Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public void saveAdmin(User admins) {
        Role role = roleRepository.findByName(TbConstants.Roles.ADMIN);

        if (role == null)
            role = roleRepository.save(new Role(TbConstants.Roles.ADMIN));


        User admin = new User(

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
