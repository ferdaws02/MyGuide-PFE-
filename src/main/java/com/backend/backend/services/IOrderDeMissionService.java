package com.backend.backend.services;

import com.backend.backend.entities.Conge;
import com.backend.backend.entities.DTOs.OrdreMissionDTO;
import com.backend.backend.entities.EtatConge;
import com.backend.backend.entities.OrdreDeMission;
import org.springframework.http.ResponseEntity;

public interface IOrderDeMissionService {
    public ResponseEntity<String> AjoutOdm(OrdreMissionDTO odm);
    public ResponseEntity<String> MettreAJourODM(OrdreMissionDTO odm);
   // public ResponseEntity<String> MettreAJourStatusCo(Long id, EtatConge Eco);
    ResponseEntity<Iterable<OrdreDeMission> >getAllODM();
}
