package com.backend.backend.controller;

import com.backend.backend.entities.Consultants;
import com.backend.backend.entities.Projet;
import com.backend.backend.entities.TypeConge;
import com.backend.backend.services.IConsultantService;
import com.backend.backend.services.IProjetService;
import com.backend.backend.services.IServiceManagerService;
import com.backend.backend.services.ITypeCongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/ref")
public class RestControllerRef {
    @Autowired
    IServiceManagerService servicemanager;
    @Autowired
    IConsultantService consult_service;
    @Autowired
    ITypeCongeService typeCongeService;

//    @PutMapping("/changerId")
//    void ChangerID(@RequestParam Long P_id, @RequestBody Consultants con){
//        consult_service.changerID(P_id,con);
//    }

     
    @PostMapping("/TypeConge")
    public void  ajoutP(@RequestBody TypeConge P){typeCongeService.AjoutTC(P);}
    
    @PutMapping("/updateTypeConge")
    void updateTC(@RequestBody TypeConge tc){typeCongeService.UpdateTC(tc);}
    @GetMapping("/AfficherTypeConge" )
    Iterable<TypeConge>getAll(){
        return typeCongeService.getALL();
    }



}
