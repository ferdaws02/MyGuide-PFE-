package com.backend.backend.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Entreprise implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_e;

    private String adresse;

    private String nomentreprise;

    private String pays;
    @OneToMany(mappedBy = "entreprise",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Manager_Client> MC= new ArrayList<>();
    @OneToOne(mappedBy = "entreprise")
    @JsonIgnore
    private Projet projet;
}
