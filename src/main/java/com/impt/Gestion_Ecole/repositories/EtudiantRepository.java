package com.impt.Gestion_Ecole.repositories;

import com.impt.Gestion_Ecole.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

}
/*
La classe NoteRepository étend l'interface JpaRepository fournie par Spring Data JPA,
ce qui vous permet d'effectuer des opérations CRUD (Create, Read, Update, Delete)
sur l'entité Note sans avoir à écrire de code supplémentaire. Vous pouvez ajouter des méthodes de requête
 personnalisées dans cette interface si vous avez besoin de fonctionnalités spécifiques.
 */
