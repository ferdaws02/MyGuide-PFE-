package com.backend.backend.services;

import com.backend.backend.entities.Entreprise;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public interface IEntrepriseService {
    public void ajoutEntreprise(Entreprise E);
    public void modifierEntreprise(Entreprise E);
    public Entreprise updateEntreprise(Long id,Entreprise E);
    ResponseEntity<Iterable<Entreprise> > consulterEntreprise();
    Entreprise getEntreprisebyID(Long id);
}
