package com.backend.backend.services;

import java.util.List;

import com.backend.backend.entities.Actions;
import com.backend.backend.entities.Formation;
import com.backend.backend.entities.RH;
import com.backend.backend.entities.DTOs.CongeDTO;

public interface IFormationService {
    void AjouterFormation(Formation formation);


    void updateFormation(Long idf,String etat);
    public List<Formation>showFormation();
}
