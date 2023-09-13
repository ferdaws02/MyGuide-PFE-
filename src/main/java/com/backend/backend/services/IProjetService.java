package com.backend.backend.services;

import com.backend.backend.entities.Consultants;
import com.backend.backend.entities.Entreprise;
import com.backend.backend.entities.Projet;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface IProjetService {
    public ResponseEntity<String> ajoutProjet(Projet P ,String e);

    public void modifierProjet(Projet P);
  Iterable<Projet> getALL();
}

