package com.impt.Gestion_Ecole.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "professeur")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Professeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prof_id", nullable = false)
    private Long prof_id;

    private String name ;
    private String  prenom ;

    private String specialite;
    private String diplome;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    @OneToMany(mappedBy = "professeur", cascade = CascadeType.ALL)

    private List<Matiere> matieres;

    @Override
    public String toString() {
        return "Professeur{" +
                "prof_id=" + prof_id +
                ", name='" + name + '\'' +
                ", prenom='" + prenom + '\'' +
                ", specialite='" + specialite + '\'' +
                ", diplome='" + diplome + '\'' +
                ", user=" + user +
                '}';
    }

}
