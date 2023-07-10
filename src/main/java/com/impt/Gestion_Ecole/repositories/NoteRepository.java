package com.impt.Gestion_Ecole.repositories;

import com.impt.Gestion_Ecole.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

}
