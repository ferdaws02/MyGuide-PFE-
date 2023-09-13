package com.backend.backend.services;

import com.backend.backend.entities.Consultants;
import com.backend.backend.entities.Service_Manager;
import org.springframework.http.ResponseEntity;

public interface IServiceManagerService extends ICompteService {

    ResponseEntity<String> AjouterSM(Service_Manager SM);
    void ModifSM(Service_Manager sm);
    Service_Manager getSM(String email);
}
