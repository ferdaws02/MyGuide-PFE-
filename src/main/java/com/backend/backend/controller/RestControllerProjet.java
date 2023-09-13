package com.backend.backend.controller;

import com.backend.backend.entities.Conge;
import com.backend.backend.entities.Projet;
import com.backend.backend.services.IPapierService;
import com.backend.backend.services.IProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/")

public class RestControllerProjet {
    @Autowired
    IProjetService P_service;
    @PostMapping("/ajoutProjet")
    public void  ajoutP(@RequestBody Projet P){P_service.ajoutProjet(P,P.getEntreprise().getNomentreprise());
    }
    @PutMapping("/modifierProjet")
    public void ModifierP(@RequestBody Projet P){P_service.modifierProjet(P);}
    @GetMapping("/ListeProjets")
    Iterable<Projet> listerProjets(){return P_service.getALL();}
}
