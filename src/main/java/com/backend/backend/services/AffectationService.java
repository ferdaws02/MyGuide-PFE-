package com.backend.backend.services;

import com.backend.backend.Repositories.IAffectationRepository;
import com.backend.backend.Repositories.IConsultantRepository;
import com.backend.backend.Repositories.IManagerInetumRepository;
import com.backend.backend.entities.*;
import com.backend.backend.entities.DTOs.AffectationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
public class AffectationService implements IAffectationService {
   @Autowired
    IAffectationRepository aff_repo;

 public static boolean isDateAfterToday(LocalDate date) {
        LocalDate today = LocalDate.now();
        return date.isAfter(today);
    }

    @Override
    public void AjouterAffectationMI(Affectation aff) {

        AffectationId id = new AffectationId();
        id.setConsultant_id(aff.getId().getConsultant_id());
        id.setManager_id(aff.getId().getManager_id());
        if (isDateAfterToday(aff.getAff_date())){
            aff.setStatus("en cour");
             aff_repo.save(aff);
        }else{
            aff.setStatus("en attente");
             aff_repo.save(aff);
        }
       
    }
    @Override
    public Iterable<Affectation> getALL() {
        List<Affectation>affs =(List<Affectation>) aff_repo.findAll();
        return  affs;
    }

    }




