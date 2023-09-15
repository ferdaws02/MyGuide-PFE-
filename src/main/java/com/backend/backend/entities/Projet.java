package com.backend.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Projet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_p;
    private String titre;
    private String description;
    @OneToOne
   // @JsonIgnore
    private Entreprise entreprise;
    @OneToMany(mappedBy = "projet",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Consultants>consult=new ArrayList<>();
    @OneToMany(mappedBy = "projetOdm",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrdreDeMission> ODMs=new ArrayList<>();


}