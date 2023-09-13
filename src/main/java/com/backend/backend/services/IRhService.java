package com.backend.backend.services;

import com.backend.backend.entities.Comptes;
import com.backend.backend.entities.RH;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface IRhService extends ICompteService{
    ResponseEntity<String> AjouterRH(RH grh);


    void DesactiverCompte(Long id, String etat);

    abstract void ModifierMDP(Long id, String mdp);

    void Modifuser(Comptes compte);

    Iterable<Comptes>getALL();
    Optional<Comptes> getById(Long id);
}
