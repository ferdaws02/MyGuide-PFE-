package com.backend.backend.entities.DTOs;

import com.backend.backend.entities.Consultants;
import com.backend.backend.entities.Manager_Client;
import com.backend.backend.entities.Projet;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

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