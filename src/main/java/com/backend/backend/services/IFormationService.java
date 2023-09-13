package com.backend.backend.services;

import com.backend.backend.entities.Actions;
import com.backend.backend.entities.Formation;
import com.backend.backend.entities.RH;

public interface IFormationService {
    void AjouterFormation(Formation formation);


    void updateFormation(Long idf,String etat);
}
