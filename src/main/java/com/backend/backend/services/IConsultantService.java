package com.backend.backend.services;

import com.backend.backend.entities.Comptes;
import com.backend.backend.entities.Consultants;
import com.backend.backend.entities.DTOs.AffectationCmcDTO;
import com.backend.backend.entities.DTOs.AffectationCmiDTO;
import com.backend.backend.entities.DTOs.AffectationDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;

import java.util.List;

    public interface IConsultantService{
    public boolean isIdExists(Long id);
    ResponseEntity<String> AjouterConsultant(Consultants consultant);

    void DesactiverCompte(Long id, String etat);

    ResponseEntity<String> AffectationCP(AffectationDTO aff);
//    void changerID(Long id, Consultants con);
    public ResponseEntity<String> AffectationCMC(AffectationCmcDTO cmcDTO) ;
    public ResponseEntity<String> AffectationCMI(AffectationCmiDTO aff) ;
    List<Comptes> getALLConsultant();

        Long getConnectedUserId(HttpSession session);

        void ajout(Comptes comptes);

        void Modifuser(Consultants compte);

        public void ModifierMDP(Long id, String mdp);

        public List<Consultants> getUsersWithNonNullStatus();
}
