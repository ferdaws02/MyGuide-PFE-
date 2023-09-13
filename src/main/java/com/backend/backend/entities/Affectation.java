package com.backend.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Affectation")
public class Affectation {
    @EmbeddedId
    private AffectationId id;
    @Temporal(TemporalType.DATE)
    private LocalDate aff_date;
    private  String status;
}
