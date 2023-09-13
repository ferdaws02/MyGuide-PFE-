package com.backend.backend.controller;

import com.backend.backend.entities.Entreprise;
import com.backend.backend.entities.Manager_Inetum;
import com.backend.backend.entities.Projet;
import com.backend.backend.services.IEntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/")
public class RestControllerEntreprise {
    @Autowired
    IEntrepriseService E_Service;
    @PostMapping("ajouter_Entreprise")
    public void ajouterMI(@RequestBody Entreprise NE){
        E_Service.ajoutEntreprise(NE);
    }
    @CrossOrigin(origins = "http://localhost:3002/Clients")
    @PutMapping("/ModifierEntreprise")
    public void upadateEntreprise(@RequestBody Entreprise E){E_Service.modifierEntreprise(E);}
    @GetMapping("/listeDesEntreprises")
    ResponseEntity<Iterable<Entreprise>> consulterListentreprise(){return E_Service.consulterEntreprise();}
    @GetMapping("/getEntreprise/{id_e}")
    public Entreprise getEntreprise(@PathVariable Long id_e){return E_Service.getEntreprisebyID(id_e);}

    @PutMapping("/updateEntreprise/{id_e}")
    public Entreprise update(@PathVariable Long id_e,@RequestBody Entreprise E){return E_Service.updateEntreprise(id_e,E);}

}


