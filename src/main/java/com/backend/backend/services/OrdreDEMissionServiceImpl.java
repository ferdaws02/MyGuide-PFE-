package com.backend.backend.services;

import com.backend.backend.Repositories.INoteDeFraisRepository;
import com.backend.backend.Repositories.IOrdreDeMissionRepository;
import com.backend.backend.entities.Conge;
import com.backend.backend.entities.EtatConge;
import com.backend.backend.entities.NoteDeFrais;
import com.backend.backend.entities.DTOs.OrdreMissionDTO;

import jakarta.transaction.Transactional;

import com.backend.backend.entities.OrdreDeMission;
import com.backend.backend.entities.Projet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrdreDEMissionServiceImpl implements IOrderDeMissionService{
    @Autowired
    IOrdreDeMissionRepository ODM_Repo;
    @Autowired
    INoteDeFraisRepository NoteDeFraisRepo;



    public static int compareDates(LocalDate date1, LocalDate date2) {
        return date1.compareTo(date2);
    }

    /**
     * @param ordre
     * @return
     */
    @Override
    public ResponseEntity<String>AjoutOdm(OrdreMissionDTO ordre){
        OrdreDeMission odm = new OrdreDeMission();
        odm.setDescription_odm(ordre.getDescription_odm());
        odm.setConsultantsOdm(ordre.getConsultantsOdm());
        odm.setProjetOdm(ordre.getProjetOdm());
        odm.setProjetOdm(ordre.getProjetOdm());
        odm.setNbr_jour_sur_site((ordre.getNbr_jour_sur_site()));
        odm.setNbr_jour_tt(ordre.getNbr_jour_tt());
        odm.setDebutodm(ordre.getDebutodm());
        odm.setFinodm(ordre.getFinodm());
        if (compareDates(odm.getDebutodm(), ordre.getFinodm()) < 0) {

            Long id_c = ordre.getConsultantsOdm().getIdc();

            ODM_Repo.save(odm);
//            if (odm.getNbr_jour_tt() == 0) {
//     // Créez et sauvegardez la note de frais ici
//     NoteDeFrais noteDeFrais = new NoteDeFrais();
//     noteDeFrais.setNbrJRSURsit(0); // Définissez la valeur appropriée pour nbrJRSURsit
//     noteDeFrais.setKmJour(0); // Définissez la valeur appropriée pour kmJour
//     noteDeFrais.setFraiskm(0); // Définissez la valeur appropriée pour fraiskm
//     noteDeFrais.setSomme(0); // Définissez la valeur appropriée pour somme
//     noteDeFrais.setOdm(odm); // Liez la note de frais à l'ordre de mission

//     NoteDeFraisRepo.save(noteDeFrais);
// }
            return new ResponseEntity(HttpStatus.CREATED);
        
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    /**
     * @param odmDTO
     * @return
     */
    @Override
    public ResponseEntity<String> MettreAJourODM(OrdreMissionDTO odmDTO) {
        OrdreDeMission om = new OrdreDeMission();
        // om.setId_odm(odmDTO.getId_odm());
        om.setNbr_jour_tt(odmDTO.getNbr_jour_tt());
        om.setDescription_odm(odmDTO.getDescription_odm());
        om.setConsultantsOdm(odmDTO.getConsultantsOdm());
        om.setDebutodm(odmDTO.getDebutodm());
        om.setFinodm(odmDTO.getFinodm());
        om.setNbr_jour_sur_site(odmDTO.getNbr_jour_sur_site());
        om.setProjetOdm(odmDTO.getProjetOdm());

        OrdreDeMission omfound= ODM_Repo.findById(om.getId_odm()).orElse(null);
        if(compareDates(om.getDebutodm(),om.getFinodm())<0){
            Long id = om.getId_odm();
            assert omfound != null;
            omfound.setProjetOdm(om.getProjetOdm());
            omfound.setDebutodm(om.getDebutodm());
            omfound.setFinodm(om.getFinodm());
            ODM_Repo.save(omfound);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    /**
     * @return
     */
    @Override
    public List<OrdreMissionDTO> getAllODM() {
        List<OrdreDeMission> ordreMissions = (List<OrdreDeMission>) ODM_Repo.findAll();
     
        List<OrdreMissionDTO> ordreMissionDTOs = new ArrayList<>();
        for (OrdreDeMission ordreDEMission : ordreMissions) {
            OrdreMissionDTO dto = new OrdreMissionDTO();
            dto.setId_odm(ordreDEMission.getId_odm());
            dto.setDebutodm(ordreDEMission.getDebutodm());
            dto.setFinodm(ordreDEMission.getFinodm());
            dto.setDescription_odm(ordreDEMission.getDescription_odm());
            dto.setStatusODM(ordreDEMission.getStatusOdm());
            if (ordreDEMission.getNdf()== null){  dto.setSomme(0);}
            else{
                dto.setSomme(ordreDEMission.getNdf().getSomme());
            }
          
            ordreMissionDTOs.add(dto);
        }
        return ordreMissionDTOs;
    }
    @Override
    public void statusAnnuler(OrdreMissionDTO odm) {
        OrdreDeMission odmnew =ODM_Repo.findById(odm.getId_odm()).orElse(null);
    odmnew.setStatusOdm("Annuler");  
    NoteDeFrais ndf=odmnew.getNdf();
    if(ndf!= null){
        ndf.setStatusNdf("Annuler");
        NoteDeFraisRepo.save(ndf);
        ODM_Repo.save(odmnew );
    }else{  ODM_Repo.save(odmnew );}
 
    }

    @Transactional
    @Override
    public ResponseEntity<String> MAJStatusNDFetODM(Long id_odm, String newEtat) {
        OrdreDeMission odm = ODM_Repo.findById(id_odm).orElse(null);
      
        odm.setStatusOdm(newEtat);
        ODM_Repo.save(odm);
        
     NoteDeFrais ndf=odm.getNdf();
    if(ndf!= null){
       ndf.setStatusNdf(newEtat);
        NoteDeFraisRepo.save(ndf);
        return new ResponseEntity<>(HttpStatus.OK);
    }else{
        odm.setStatusOdm("Valider");
        ODM_Repo.save(odm);
       return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
