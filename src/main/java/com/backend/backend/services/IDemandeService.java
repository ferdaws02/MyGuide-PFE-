package com.backend.backend.services;

import com.backend.backend.entities.Consultants;
import com.backend.backend.entities.Demandes;


public interface IDemandeService {
    void AjouterDemande(Demandes demande);


    void updateDemande(Long idf, String etat);
}
