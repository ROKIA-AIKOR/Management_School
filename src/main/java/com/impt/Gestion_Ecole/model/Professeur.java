package com.impt.Gestion_Ecole.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


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
    private User user;
}
