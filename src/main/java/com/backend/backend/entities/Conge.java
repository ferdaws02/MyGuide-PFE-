package com.backend.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Conge implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_co;
    @Temporal(TemporalType.DATE)
    private LocalDate ddconge;
    @Temporal(TemporalType.DATE)
    private LocalDate dfconge;

    @Enumerated(EnumType.STRING)
    private EtatConge etat;

    @ManyToOne
    @JoinColumn(name="congeconsultant")
    @JsonIgnore
    private Consultants consultant;
    @ManyToOne
    @JoinColumn(name="type_conge")
    @JsonIgnore
    private TypeConge typeConge;



}
