package com.impt.Gestion_Ecole.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "matiere")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Matiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matiere_id", nullable = false)
    private Long matiereId;

    private String nom;
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prof_id")
    private Professeur professeur;

    @Override
    public String toString() {
        return "Matiere{" +
                "matiereId=" + matiereId +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                '}';
    }




}
