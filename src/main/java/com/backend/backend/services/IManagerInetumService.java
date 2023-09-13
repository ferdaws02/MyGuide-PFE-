package com.backend.backend.services;

import com.backend.backend.entities.Comptes;
import com.backend.backend.entities.Manager_Inetum;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IManagerInetumService extends ICompteService {
    ResponseEntity<String> AjouterMI(Manager_Inetum mi);

    void Modifuser(Manager_Inetum compte);
    List<Comptes>getALLMI();



}
