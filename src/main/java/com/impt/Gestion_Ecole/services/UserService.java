package com.impt.Gestion_Ecole.services;

import com.impt.Gestion_Ecole.model.User;

public interface UserService {

    void saveProf(User profs);
    void saveAdmin(User admins);
    void saveEtudiante(User etudiantes);
    User findUserByEmail(String email);
}
