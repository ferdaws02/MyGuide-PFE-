package com.backend.backend.services;

import com.backend.backend.entities.Affectation;

public interface IAffectationService {
    void AjouterAffectationMI(Affectation aff);
    Iterable<Affectation> getALL();
}
