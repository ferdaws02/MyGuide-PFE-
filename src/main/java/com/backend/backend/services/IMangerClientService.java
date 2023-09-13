package com.backend.backend.services;

import com.backend.backend.entities.Comptes;
import com.backend.backend.entities.Manager_Client;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMangerClientService extends ICompteService{
    void ModifierMDP(Long id, String mdp);

    List<Comptes> getMC();

    void Modifuser(Manager_Client compte);

    public ResponseEntity<String> AjouterMC(Manager_Client mc);
    public ResponseEntity<String> UpdateAll(Manager_Client mc,String nomE);
    public List<Comptes> getALLMC();
}
