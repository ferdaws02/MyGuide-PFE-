package com.backend.backend.services;

import com.backend.backend.dto.EtatCongeDTO;
import com.backend.backend.entities.Conge;
import com.backend.backend.entities.Consultants;
import com.backend.backend.entities.DTOs.CongeDTO;
import com.backend.backend.entities.EtatConge;
import com.backend.backend.entities.TypeConge;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICongeService {
    public ResponseEntity<String> AjoutConge(CongeDTO co, TypeConge Tco,Consultants con);
    public ResponseEntity<String> MettreAJourCo(CongeDTO coDto, String Tco);
    public ResponseEntity<String> MettreAJourStatusCo(Long id, EtatConge Eco);
   Iterable<Conge> getALL();
    public List<Conge> getCongesByConsultantId(Long consultantId);
    public Long SoldeDeCongeRestant(Long Con_id);



}
