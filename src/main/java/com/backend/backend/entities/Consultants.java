package com.backend.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "Consultants")
@Getter
@Setter

public class Consultants extends Comptes  {


    private String post_c;
    private String pole_c;
    private String status;
    @Temporal(TemporalType.DATE)
    private LocalDate ddaff_projet;
    @Temporal(TemporalType.DATE)
    private LocalDate dfaff_projet;
    private  float soldecongémaladie;
    private  float SoldeCongéPayé;
    @OneToMany(mappedBy = "consultant",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Formation> formation=new ArrayList<>();
    @OneToMany(mappedBy = "consultant_demande",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Demandes> demandes=new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private Projet projet;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="managerclient")
    private Manager_Client managerclient;
    @ManyToMany(mappedBy = "consultants")
    @JsonIgnore
    private Set<Manager_Inetum> managers = new HashSet<>();
    @OneToMany(mappedBy = "consultant",cascade = CascadeType.ALL)
    private List<Conge> conges=new ArrayList<>();
    @OneToMany(mappedBy = "consultantsOdm",cascade = CascadeType.ALL)
    private List<OrdreDeMission> odms=new ArrayList<>();

    // Consultants(Long id_c, String nom_c, String prenom_c, String cin_c, String adresse_c, Date ddn_c, String photo_c, String sexe_c, String num_tel_c, String etat, String emailc, Roles roles, String mdp_c, Service_Manager serviceManager) {
    //     super(id_c, nom_c, prenom_c, cin_c, adresse_c, ddn_c, photo_c, sexe_c, num_tel_c, etat, emailc, roles, mdp_c, serviceManager);
    // }

    public Consultants() {
        super();
    }
}
