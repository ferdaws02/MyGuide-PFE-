package com.backend.backend.controller;

import com.backend.backend.entities.*;
import com.backend.backend.services.IDemandeService;
import com.backend.backend.services.IFormationService;
import com.backend.backend.services.IPapierService;
import com.backend.backend.services.IPlanningServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/DemandeEtFormation")
public class RestControllerDemandeEtFormation {
    @Autowired
    IFormationService formaservice;
    @Autowired
    IDemandeService demande_service;
    @Autowired
    IPapierService pap_serv;
    @Autowired
    IPlanningServices plan_ser;
    @PostMapping("/ajouter_Formation")
    public void ajouterFormation(@RequestBody Formation formation){
        formaservice.AjouterFormation(formation);
    }
    @PostMapping("/ajouter_Demande")
    public void ajouterDemande(@RequestBody Demandes demande){
        demande_service.AjouterDemande(demande);
    }
    @PostMapping("/ajouter_Papier")
    public void ajouterPapier(@RequestBody Papierdemande papier){pap_serv.AjouterPapier(papier);
    }
    @PostMapping("/ajouter_Planning")
    public void ajouterPlanning(@RequestBody Planning planning){
        plan_ser.AjouterPlaning(planning);
    }
    @PutMapping("/change_etat_formation/{idf}")
    public void ChangeStatus(@PathVariable Long idf,@RequestBody String etat){
        formaservice.updateFormation(idf,etat);
    }
    @PutMapping("/change_etat_demande/{idd}")
    public void ChangeStatusDemande(@PathVariable Long idd,@RequestBody String etat){
        demande_service.updateDemande(idd,etat);
    }

}
