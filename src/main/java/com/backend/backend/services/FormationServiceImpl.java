package com.backend.backend.services;

import com.backend.backend.Repositories.IFormationRepository;
import com.backend.backend.entities.Formation;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FormationServiceImpl implements IFormationService {
    @Autowired
    IFormationRepository forma_repo;
    @Override
    public void AjouterFormation(Formation formation) {
        formation.setStatus("En_cours");
         formation.setEtat("En_cours");
        forma_repo.save(formation);
    }

    @Override
    public void updateFormation(Long idf, String etat) {
        Formation formation=forma_repo.findById(idf).orElse(null);
        formation.setEtat(etat);
        forma_repo.save(formation);
    }

    @Override
    public List<Formation> showFormation() {
     List<Formation> formations=(List<Formation>) forma_repo.findAll();
     return formations;
    }


}
