package com.backend.backend.entities.DTOs;

import com.backend.backend.entities.Consultants;
import com.backend.backend.entities.Entreprise;
import com.backend.backend.entities.Manager_Client;
import com.backend.backend.entities.Projet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Getter
@Setter
public class AffectationDTO {
    private Consultants consultant;
    private Projet projet;
    @Temporal(TemporalType.DATE)
    private LocalDate ddaff_projet;
    @Temporal(TemporalType.DATE)
    private LocalDate dfaff_projet;
    private String status_c;
    private Manager_Client entreprise;
}
