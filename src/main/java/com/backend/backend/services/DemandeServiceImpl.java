package com.backend.backend.services;

import com.backend.backend.Repositories.IDemandeRepository;
import com.backend.backend.entities.Demandes;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DemandeServiceImpl implements IDemandeService{
    @Autowired
    IDemandeRepository demande_repo;
    @Override
    public void AjouterDemande(Demandes demande) {
        demande_repo.save(demande);
    }

    @Override
    public void updateDemande(Long idf, String etat) {
        Demandes demande=demande_repo.findById(idf).orElse(null);
        demande.setStatus(etat);
        demande_repo.save(demande);
    }

    @Override
    public List<Demandes> showDemande() {
      List<Demandes> Ds=(List<Demandes>) demande_repo.findAll();
      return Ds;
    }
}
