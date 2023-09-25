package com.backend.backend.services;


import com.backend.backend.entities.DTOs.OrdreMissionDTO;
import com.backend.backend.entities.OrdreDeMission;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface IOrderDeMissionService {
    public ResponseEntity<String> AjoutOdm(OrdreMissionDTO odm);
    public ResponseEntity<String> MettreAJourODM(OrdreMissionDTO odm);
   // public ResponseEntity<String> MettreAJourStatusCo(Long id, EtatConge Eco);
    public List<OrdreMissionDTO> getAllODM() ;
    public void statusAnnuler(OrdreMissionDTO odm);
    public ResponseEntity<String> MAJStatusNDFetODM(Long id_odm, String newEtat); 
}
