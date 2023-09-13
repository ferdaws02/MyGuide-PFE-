package com.backend.backend.entities;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.NaturalId;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;


import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Service_Manager extends Comptes implements Serializable {
//    @Id
//    @Column(name = "id_sm")
//    private Long id_sm;
//
//    private String nom_sm;
//
//    private String prenom_sm;
//
//    private String adresse_sm;
//
//    private String num_tel_sm;
//    @NaturalId(mutable = true)
//    private String email;
//
//    private String mdp_sm;
//
//    @Temporal(TemporalType.DATE)
//    private Date ddn_sm;
//
//    private String photo_sm;
//
//    private String sexe_sm;
//
//    private String cin_sm;
//    private String roles;
    @JsonIgnore
    @OneToMany(mappedBy = "serviceManager", cascade = CascadeType.ALL)
    private List<Comptes> compte = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "smAction", cascade = CascadeType.ALL)
    private List<Actions> action = new ArrayList<>();


}