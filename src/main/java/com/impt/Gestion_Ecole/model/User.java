package com.impt.Gestion_Ecole.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long user_id;

    private String name ;
    private String  prenom ;
    private String username;


    private String email;


    private String password;

@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name="users_roles",
            joinColumns = {@JoinColumn(name = "user_id" ,referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName ="role_id")}
    )
    //private Set<Role> roles = new HashSet<>();
    private List<Role> roles = new ArrayList<>();





    public User(String name,  String email,String username , String password, List<Role> roles) {
        this.name = name;
        this.username = username;
        this.email= email;
        this.password = password;
        this.roles = roles;
    }

    public User( String email, String password, List<Role> roles) {

        this.email= email;
        this.password = password;
        this.roles = roles;
    }
}
