package com.backend.backend.entities.DTOs;

import com.backend.backend.entities.Consultants;
import com.backend.backend.entities.EtatConge;
import com.backend.backend.entities.TypeConge;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class CongeDTO {
    private Long id_co;
    @Temporal(TemporalType.DATE)
    private LocalDate ddconge;
    @Temporal(TemporalType.DATE)
    private LocalDate dfconge;
    @Enumerated(EnumType.STRING)
    private EtatConge etat;
    private Consultants consultant;
    private TypeConge typeConge;

}
