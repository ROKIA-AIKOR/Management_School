package com.impt.Gestion_Ecole.model;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "notes")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String note1;
    private String note2;
    private String note3;
    private String noteFinale;
    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "matiere_id")
    private Matiere matiere;

    @ManyToOne
    @JoinColumn(name = "prof_id")
    private Matiere professeur;



    public void calculateNoteFinale() {
        double sum = Double.parseDouble(note1) + Double.parseDouble(note2) + Double.parseDouble(note3);
        double average = sum / 3;
        noteFinale = String.valueOf(average);
    }
}

