package com.backend.backend.services;

import com.backend.backend.entities.Conge;
import com.backend.backend.entities.DTOs.OrdreMissionDTO;
import com.backend.backend.entities.EtatConge;
import com.backend.backend.entities.OrdreDeMission;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IOrderDeMissionService {
    public ResponseEntity<String> AjoutOdm(OrdreMissionDTO odm);
    public ResponseEntity<String> MettreAJourODM(OrdreMissionDTO odm);
   // public ResponseEntity<String> MettreAJourStatusCo(Long id, EtatConge Eco);
   List<OrdreMissionDTO> getAllODM();
    public void statusAnnuler(OrdreMissionDTO odm);
    public ResponseEntity<String> MAJStatusNDFetODM(Long id_odm, String newEtat);
}
