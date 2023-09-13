package com.backend.backend.entities.DTOs;

import com.backend.backend.entities.Consultants;
import com.backend.backend.entities.Projet;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class OrdreMissionDTO {
    private Long id_odm;
    private LocalDate debutodm;
    private LocalDate finodm;
    private String Description_odm;
    private int nbr_jour_tt;
    private int nbr_jour_sur_site;
    @ManyToOne
    @JoinColumn(name="consultantsOdm",referencedColumnName = "id_c")
    private Consultants consultantsOdm;

    @ManyToOne
    @JoinColumn(name="projetOdm")
    private Projet projetOdm;
}
