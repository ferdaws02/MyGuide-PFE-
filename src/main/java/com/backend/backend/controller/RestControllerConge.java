package com.backend.backend.controller;

import com.backend.backend.Repositories.IConsultantRepository;
import com.backend.backend.Repositories.ITypeCongeReposiory;
import com.backend.backend.dto.EtatCongeDTO;
import com.backend.backend.entities.Comptes;
import com.backend.backend.entities.Conge;
import com.backend.backend.entities.Consultants;
import com.backend.backend.entities.DTOs.CongeDTO;
import com.backend.backend.entities.EtatConge;
import com.backend.backend.entities.TypeConge;
import com.backend.backend.services.ICongeService;
import jakarta.servlet.http.HttpSession;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/Conge")
public class RestControllerConge {
    @Autowired
    ICongeService co_Ser;
    @Autowired
    IConsultantRepository co_Repo;
     @Autowired
    ITypeCongeReposiory Tco_Repo;
   @PostMapping("/AddConge")
public ResponseEntity<String> AddCo(HttpSession session, @RequestBody CongeDTO co) {
    Consultants profileData = new Consultants();
    profileData.setIdc((Long) session.getAttribute("user_id"));

    // Fetch the TypeConge object based on the string value co.getTypeConge().getType()
    String typeCongeString = co.getTypeConge().getType();
    TypeConge typeConge = Tco_Repo.findTypeCongeByType(typeCongeString);

    // Check if the TypeConge exists and then call AjoutConge with the TypeConge object
    if (typeConge != null) {
        return co_Ser.AjoutConge(co, typeConge, profileData);
    } else {
        // Handle the case where the TypeConge does not exist
        return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("TypeConge not found");
    }
}

    @PutMapping("/updateConge")
    public ResponseEntity<String> updateCo(@RequestBody CongeDTO co ){return co_Ser.MettreAJourCo(co,co.getTypeConge().getType());}



    @PutMapping("/updateConge/{id_co}")
    public ResponseEntity<String> updateStatusCo(@PathVariable Long id_co, @RequestBody CongeDTO etatCongeDTO) {
        // Extract the status field from the JSON object

        return co_Ser.MettreAJourStatusCo(id_co, etatCongeDTO.getEtat());

    }

    @GetMapping("/showAll")
    Iterable<Conge> listerConges(){return co_Ser.getALL();}
    @GetMapping("/{consultantId}")
    public List<Conge> getCongesByConsultantId(@PathVariable("consultantId") Long consultantId) {
        return co_Ser.getCongesByConsultantId(consultantId);
    }
    @GetMapping("Solde/{consultantId}")
    public Long getSoldCongeRestant(@PathVariable("consultantId") Long consultantId) {
        return co_Ser.SoldeDeCongeRestant(consultantId);
    }
}
