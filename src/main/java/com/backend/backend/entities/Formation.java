package com.backend.backend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Formation extends Demandes implements Serializable {
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private Long id_f;
    private String nom_formation;
    @Enumerated(EnumType.STRING)
    private Niveau niveau;

    private String commentaire;
    private String etat;
 

    @OneToOne(mappedBy = "formation")
    private Planning planning;
}


