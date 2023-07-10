package com.impt.Gestion_Ecole.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "etudiants")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Etudiant {

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "etudiant_id", nullable = false)
    private Long etudiant_id;

    private String name;
    private String prenom;
    private String adresse;
    private String date_naissance;
    private String niveau;

    private String specialite;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    private List<Matiere> matieres;


    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    private List<Note> notes;
    @Override
    public String toString() {
        return "Etudiant{" +
                "etudiant_id=" + etudiant_id +
                ", name='" + name + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", date_naissance='" + date_naissance + '\'' +
                ", niveau='" + niveau + '\'' +
                ", specialite='" + specialite + '\'' +
                ", user=" + user +
                ", matieres=" + matieres +
                '}';
    }
}
