package com.backend.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdreDeMission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_odm;
    private LocalDate debutodm;
    private LocalDate finodm;
    private String Description_odm;
    private int nbr_jour_tt;
    private int nbr_jour_sur_site;
    private String statusOdm; 
    @ManyToOne
    @JoinColumn(name="consultantsOdm",referencedColumnName = "id_c")
    @JsonIgnore
    private Consultants consultantsOdm;

    @ManyToOne
    @JoinColumn(name="projetOdm")
    @JsonIgnore
    private Projet projetOdm;


    @JsonManagedReference
    @OneToOne
    // @JsonIgnore
     private NoteDeFrais ndf;
}