package com.backend.backend.entities;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    // @JsonIgnore
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

    public Consultants() {
        super();
    }
}