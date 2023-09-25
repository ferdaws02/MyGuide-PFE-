package com.backend.backend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
@Data
@Builder
@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public class Demandes{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_d;
    @Temporal(TemporalType.DATE)
    private Date date_demande;
    private String commentaire;
    private String Status;
    @ManyToOne
    @JoinColumn(name = "Rh_demande")
    @JsonProperty
    private RH rh_demande;
    @ManyToOne
   @JoinColumn(name = "consultant_demande")
   @JsonProperty
    private Consultants consultant_demande;
    @OneToMany(mappedBy = "Papier",cascade = CascadeType.ALL)
    private List<Papierdemande> papier=new ArrayList<>();
}
