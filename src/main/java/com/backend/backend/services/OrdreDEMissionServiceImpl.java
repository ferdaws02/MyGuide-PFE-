package com.backend.backend.services;

import com.backend.backend.Repositories.IOrdreDeMissionRepository;
import com.backend.backend.entities.Conge;
import com.backend.backend.entities.DTOs.OrdreMissionDTO;
import com.backend.backend.entities.OrdreDeMission;
import com.backend.backend.entities.Projet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class OrdreDEMissionServiceImpl implements IOrderDeMissionService{
    @Autowired
    IOrdreDeMissionRepository ODM_Repo;



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
            return new ResponseEntity(HttpStatus.CREATED);
        }return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    /**
     * @param odmDTO
     * @return
     */
    @Override
    public ResponseEntity<String> MettreAJourODM(OrdreMissionDTO odmDTO) {
        OrdreDeMission om = new OrdreDeMission();
        om.setId_odm(odmDTO.getId_odm());
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
    public ResponseEntity<Iterable<OrdreDeMission>> getAllODM() {
        Iterable<OrdreDeMission> odm =  ODM_Repo.findAll();
        return new ResponseEntity(odm, HttpStatus.OK);

    }
}
