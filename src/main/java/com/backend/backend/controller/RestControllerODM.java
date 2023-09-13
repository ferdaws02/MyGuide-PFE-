package com.backend.backend.controller;

import com.backend.backend.entities.Conge;
import com.backend.backend.entities.DTOs.OrdreMissionDTO;
import com.backend.backend.entities.OrdreDeMission;
import com.backend.backend.services.IOrderDeMissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/ODM")
public class RestControllerODM {
    @Autowired
    IOrderDeMissionService odm_Service;

    @PostMapping("/ajoutODM")
    public ResponseEntity<String>addODM(@RequestBody OrdreMissionDTO odm){return odm_Service.AjoutOdm(odm);}
    @PutMapping("/MAJODM")
    public ResponseEntity<String>updateODM(@RequestBody OrdreMissionDTO odm){return odm_Service.MettreAJourODM(odm);}
    @GetMapping("/showODM")
    public ResponseEntity<Iterable<OrdreDeMission>>showODM(){return odm_Service.getAllODM();}

}
