package com.backend.backend.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteDeFrais implements Serializable {
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ndf;
    private float nbrJRSURsit;
    private float kmJour;
    private float fraiskm;
    private float somme;
    private String statusNdf;
    @OneToOne(mappedBy = "ndf")
    @JsonBackReference
    private OrdreDeMission odm;
}
