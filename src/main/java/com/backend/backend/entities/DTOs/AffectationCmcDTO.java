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
public class AffectationCmcDTO {
    private Consultants consultant;
    private Manager_Client managerclient;

}
