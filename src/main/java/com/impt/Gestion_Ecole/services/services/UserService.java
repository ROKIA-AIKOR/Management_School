package com.impt.Gestion_Ecole.services.services;

import com.impt.Gestion_Ecole.model.User;

public interface UserService {
    public User createUser(User user);
    void saveProf(User profs);
    void saveAdmin(User admins);
    void saveEtudiante(User etudiantes);
    User findUserByEmail(String email);
}
