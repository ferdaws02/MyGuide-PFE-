package com.backend.backend.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class Comptables extends Comptes implements Serializable {

//    private String Nom_Comptable;
//    private String Prenom_Comptable;
//    private String CIN_Comptable;
//    private String Adresse_Comptable;
////    private String Email_Comptable;
////    private String MDP_Comptable;
//    private String photo_Comptable;
//
//    private String Sexe_Comptable;
//    private String Num_Tel_Comptable;
//    @Temporal(TemporalType.DATE)
//    private Date DDN_Comptable;

}
