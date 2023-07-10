package com.impt.Gestion_Ecole.services.services;

import com.impt.Gestion_Ecole.model.Note;

import java.util.List;

public interface NoteService {

    Note createNote(Note note);

    List<Note> getAllNotes();

    Note getNoteById(Long id);

    Note updateNote(Note note);

    void deleteNoteById(Long id);
}

