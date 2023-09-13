package com.backend.backend.controller;

import com.backend.backend.entities.Affectation;
import com.backend.backend.entities.DTOs.AffectationCmcDTO;
import com.backend.backend.entities.DTOs.AffectationDTO;
import com.backend.backend.entities.Consultants;
import com.backend.backend.services.AffectationService;
import com.backend.backend.services.IConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/affectations")
public class AffectationController {
    @Autowired
    private AffectationService affectationService;
    @Autowired
    IConsultantService consService;

//    @PostMapping("/managers/{managerId}/consultants")
//    public ResponseEntity<Void> affecterConsultants(@PathVariable Long managerId,
//                                                    @RequestBody AffectationDTO affectationDTO) {
//        affectationService.affecterConsultants(managerId, affectationDTO);
//        return ResponseEntity.ok().build();
//    }
    @PutMapping("/affectationProjet")
    public ResponseEntity<String> affectation(@RequestBody AffectationDTO c ){
        return consService.AffectationCP(c);
    }
    @PutMapping("/affectationMC")
    public ResponseEntity<String> affectationMC(@RequestBody AffectationCmcDTO c){
        return consService.AffectationCMC(c);
    }
    @PostMapping("/affectation_MI")
  
  public void affectationMI(@RequestBody Affectation aff){affectationService.AjouterAffectationMI(aff);}


  @GetMapping("/listeAffectationMI")
  Iterable<Affectation> consulterListentreprise(){return affectationService.getALL();}
}
