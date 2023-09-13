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
public class Formation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_f;
    private String nom_formation;
    @Enumerated(EnumType.STRING)
    private Niveau niveau;

    private String commentaire;
    private String etat;
    @ManyToOne
    @JoinColumn(name = "consultant")
    @JsonProperty
    private Consultants consultant;

    @OneToOne(mappedBy = "formation")
    private Planning planning;
}
//    @ManyToOne
//    @JoinColumn(name = "rh")
//    @JsonProperty
//    private RH rh;
//

