package com.impt.Gestion_Ecole.controllers;

import com.impt.Gestion_Ecole.model.Etudiant;
import com.impt.Gestion_Ecole.model.Matiere;
import com.impt.Gestion_Ecole.model.Note;
import com.impt.Gestion_Ecole.services.services.EtudiantService;
import com.impt.Gestion_Ecole.services.services.MatiereService;
import com.impt.Gestion_Ecole.services.services.NoteService;
import com.impt.Gestion_Ecole.services.services.ProfesseurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class NoteController {
    private final NoteService noteService;
    private final EtudiantService etudiantService;
    private final MatiereService matiereService;

    public NoteController(NoteService noteService, EtudiantService etudiantService, MatiereService matiereService) {
        this.noteService = noteService;
        this.etudiantService = etudiantService;
        this.matiereService = matiereService;
    }


    /*******************************
     *
     * Affiche la liste des notes
     *
     *******************************/
    @GetMapping("/note-list")
    public String getAllNotes(Model model) {
        List<Note> notes = noteService.getAllNotes();
        model.addAttribute("notes", notes);
        return "note/note-list";
    }


    /*******************************
     *
     * Affiche une note par son ID
     *
     *******************************/
    @GetMapping("/detail-notes/{id}")
    public String getNoteById(@PathVariable("id") Long id, Model model) {
        Note note = noteService.getNoteById(id);
        model.addAttribute("note", note);
        return "note/note-details";
    }


    /*******************************
     *
     * Ajouter d'une note
     *
     *******************************/
    @GetMapping("/note-form")
    public String showAddNoteForm(Model model) {
        Note note = new Note();
        List<Etudiant> etudiants = etudiantService.getAllEtudiants();
        List<Matiere> matieres = matiereService.getAllMatieres();
        model.addAttribute("note", note);
        model.addAttribute("etudiants", etudiants);
        model.addAttribute("matieres", matieres);
        return "note/note-form";
    }

    // Ajoute une nouvelle note
    @PostMapping("/note-form")
    public String addNote(@ModelAttribute("note") Note note) {
        note.calculateNoteFinale();
        noteService.createNote(note);
        return "redirect:/note-list";
    }

    /*******************************
     *
     * Modifier une note
     *
     *******************************/
    @GetMapping("/notes/edit/{id}")
    public String showUpdateNoteForm(@PathVariable("id") Long id, Model model) {
        Note note = noteService.getNoteById(id);
        model.addAttribute("note", note);
        return "note/note-modification";
    }

    @PostMapping("/notes/edit/{id}")
    public String updateNote(@PathVariable("id") Long id, @ModelAttribute Note note) {
        Note existingNote = noteService.getNoteById(id);
        if (existingNote == null) {
            return "redirect:/note-list";
        }
        existingNote.setNote1(note.getNote1());
        existingNote.setNote2(note.getNote2());
        existingNote.setNote3(note.getNote3());
        existingNote.calculateNoteFinale();
        noteService.updateNote(existingNote);
        return "redirect:/note-list";
    }




    /*******************************
     *
     * Supprimer une note
     *
     *******************************/
    @GetMapping("/delete-notes/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteService.deleteNoteById(id);
        return "redirect:/note-list";
    }
}
