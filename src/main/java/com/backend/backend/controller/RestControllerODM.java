package com.backend.backend.controller;

import com.backend.backend.Repositories.IConsultantRepository;
import com.backend.backend.Repositories.INoteDeFraisRepository;
import com.backend.backend.Repositories.IOrdreDeMissionRepository;
import com.backend.backend.entities.Consultants;
import com.backend.backend.entities.NoteDeFrais;
import com.backend.backend.entities.DTOs.CongeDTO;
import com.backend.backend.entities.DTOs.OrdreMissionDTO;
import com.backend.backend.entities.OrdreDeMission;
import com.backend.backend.services.INoteDeFraisService;
import com.backend.backend.services.IOrderDeMissionService;

import java.time.Period;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/ODM")
public class RestControllerODM {
    @Autowired
    IOrderDeMissionService odm_Service;
    @Autowired
    private IOrdreDeMissionRepository ordreDeMissionRepository;
    @Autowired
    private INoteDeFraisRepository noteDeFraisRepository;
      @Autowired
    private IConsultantRepository co_repo;
    @Autowired
    private INoteDeFraisService ndf_service;

    @PostMapping("/ajoutODM")
    public ResponseEntity<String>addODM(@RequestBody OrdreMissionDTO odm){return odm_Service.AjoutOdm(odm);}
    @PutMapping("/MAJODM")
    public ResponseEntity<String>updateODM(@RequestBody OrdreMissionDTO odm){return odm_Service.MettreAJourODM(odm);}
    @GetMapping("/showODM")
    public ResponseEntity<List<OrdreMissionDTO>> getAllOrdreMissions() {
        List<OrdreMissionDTO> ordreMissions = odm_Service.getAllODM(); // Implement this service method
        return ResponseEntity.ok(ordreMissions);
    }
 @PostMapping("/ajouter")
    public ResponseEntity<String> ajouterOrdreMissionAvecNoteDeFrais(@RequestBody OrdreMissionDTO ordreMissionDTO) {
    try {
        // Créer un nouvel ordre de mission à partir des données reçues
        OrdreDeMission ordreDeMission = new OrdreDeMission();
        ordreDeMission.setDebutodm(ordreMissionDTO.getDebutodm());
        Long id = ordreMissionDTO.getConsultantsOdm().getIdc();
        Consultants c = (Consultants) co_repo.findById(id).orElse(null);
        ordreDeMission.setConsultantsOdm(c);
        ordreDeMission.setFinodm(ordreMissionDTO.getFinodm());
        ordreDeMission.setDescription_odm(ordreMissionDTO.getDescription_odm());
        ordreDeMission.setNbr_jour_tt(ordreMissionDTO.getNbr_jour_tt());
        ordreDeMission.setNbr_jour_sur_site(ordreMissionDTO.getNbr_jour_sur_site());
        ordreDeMission.setStatusOdm("En cour de validation SM");
     // Enregistrez d'abord l'ordre de mission dans la base de données
        ordreDeMissionRepository.save(ordreDeMission);
        // Calculer la période entre debutodm et finodm
        Period periode = Period.between(ordreDeMission.getDebutodm(), ordreDeMission.getFinodm());

        // Vérifier si nbr_jour_tt est valide pour générer une note de frais
        if (ordreMissionDTO.getNbr_jour_sur_site() > 0 && ordreMissionDTO.getNbr_jour_sur_site() <= periode.getDays()) {
            // Créer une nouvelle note de frais
            NoteDeFrais noteDeFrais = new NoteDeFrais();
            noteDeFrais.setNbrJRSURsit(ordreMissionDTO.getNbr_jour_sur_site());
            noteDeFrais.setKmJour(ordreMissionDTO.getKmJour());
            noteDeFrais.setFraiskm(ordreMissionDTO.getFraiskm());
            float somme= ordreMissionDTO.getKmJour()*ordreMissionDTO.getNbr_jour_sur_site()*ordreMissionDTO.getFraiskm();
             noteDeFrais.setStatusNdf("validation_SM");
            noteDeFrais.setSomme(somme);

            // Associez la note de frais à l'ordre de mission
            noteDeFrais.setOdm(ordreDeMission);

            // Enregistrez la note de frais dans la base de données
            noteDeFraisRepository.save(noteDeFrais);
            ordreDeMission.setNdf(noteDeFrais);
        }

        // Enregistrez l'ordre de mission dans la base de données
        ordreDeMissionRepository.save(ordreDeMission);

        return new ResponseEntity<>("Données enregistrées avec succès", HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>("Erreur lors de l'enregistrement des données", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
 @PutMapping("/anuuleStatus")
    public void updatestatus(@RequestBody OrdreMissionDTO odm){odm_Service.statusAnnuler(odm);}
     @GetMapping("/showNDF")
    public ResponseEntity<Iterable<NoteDeFrais>>showNDF(){return ndf_service.getAllNDF();}
    
    @PutMapping("/update/{id_odm}")
    public ResponseEntity<String> updateStatusCo(@PathVariable Long id_odm, @RequestBody Map<String, String> requestBody) {
        // Extract the "etat" field from the JSON object
        String etat = requestBody.get("etat");
    
        return odm_Service.MAJStatusNDFetODM(id_odm, etat);
    }
    @PutMapping("NDF/update/{id_ndf}")
    public ResponseEntity<String> updateStatusNDF(@PathVariable Long id_ndf, @RequestBody Map<String, String> requestBody) {
        // Extract the "etat" field from the JSON object
        String etat = requestBody.get("etat");
    
        return ndf_service.MAJStatusNDF(id_ndf, etat);
    }
    
}