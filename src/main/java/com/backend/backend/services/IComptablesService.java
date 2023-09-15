package com.backend.backend.services;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.backend.backend.entities.Comptables;
import com.backend.backend.entities.Comptes;


public interface IComptablesService extends ICompteService{

  ResponseEntity<String> AjouterComptable(Comptables ctp);
    void DesactiverCompte(Long id, String etat);

    abstract void ModifierMDP(Long id, String mdp);

    void Modifuser(Comptes compte);

    Iterable<Comptes>getALL();
    Optional<Comptes> getById(Long id);
}
