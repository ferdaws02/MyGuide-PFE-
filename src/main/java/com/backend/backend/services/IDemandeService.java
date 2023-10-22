package com.backend.backend.services;

import java.util.List;

import com.backend.backend.entities.Demandes;


public interface IDemandeService {
    void AjouterDemande(Demandes demande);
List<Demandes> showDemande();

    void updateDemande(Long idf, String etat);
}
