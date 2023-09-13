package com.backend.backend.services;

import com.backend.backend.entities.Comptes;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface ICompteService {
    void DesactiverCompte(Long id,String etat);
    void ModifierMDP(Long id,String mdp);

    public Long getConnectedUserId(HttpSession session);
    void ajout(Comptes comptes);

    public void saveFile(MultipartFile file, Long compteId) throws IOException;


}
