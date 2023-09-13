package com.backend.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RH extends Comptes implements Serializable {

//    private String nom_rh;
//    private String prenom_rh;
//    private String cin_rh;
//    private String adresse_rh;
////    private String email_rh;
////    private String mdp_rh;
//    private String photo_rh;
//
//    private String sexe_rh;
//    private String num_tel_rh;
//    @Temporal(TemporalType.DATE)
//    private Date ddn_rh;

    @OneToMany(mappedBy = "rh_p",cascade = CascadeType.ALL)
    private List<Planning> planing=new ArrayList<>();
    @OneToMany(mappedBy = "rh_demande",cascade = CascadeType.ALL)
    private List<Demandes> demandes=new ArrayList<>();
//    @OneToMany(mappedBy = "rh",cascade = CascadeType.ALL)
//    private List<Formation> demandesF=new ArrayList<>();


}
