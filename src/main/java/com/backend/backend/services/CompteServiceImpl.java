package com.backend.backend.services;

import com.backend.backend.Repositories.IComptesRepository;

import com.backend.backend.Repositories.IConsultantRepository;

import com.backend.backend.entities.Comptes;
import com.backend.backend.entities.Consultants;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.io.IOException;


@Service
@Slf4j
@RequiredArgsConstructor

public class CompteServiceImpl  implements ICompteService

{

    @Autowired
    IComptesRepository com_rep;
    @Override
    public void saveFile(MultipartFile file, Long compteId) throws IOException {
        // Get the Comptes entity by its "id_c" from the database
        Comptes compte = com_rep.findByIdc(String.valueOf(compteId));
        if (compte == null) {
            throw new IllegalArgumentException("Compte not found with id_c: " + compteId);
        }

        // Convert the MultipartFile to a byte array
        byte[] fileBytes = file.getBytes();

        // Set the photo_c attribute of the Comptes entity with the byte array
        compte.setPhoto_c(fileBytes);

        // Save the updated Comptes entity to the database
        com_rep.save(compte);
    }
    public void DesactiverCompte(Long id, String etat) {
        Comptes compte = com_rep.findByIdc(String.valueOf(id));
        if (compte != null) {
            compte.setEtat(etat);
            com_rep.save(compte);
        } else {
            throw new IllegalArgumentException("Compte not found with id_c: " + id);
        }
    }


    public void ModifierMDP(Long id, String mdp) {
        Comptes compte = com_rep.findByIdc(String.valueOf(id));
        if (compte != null) {
            compte.setMdp_c(mdp);
            com_rep.save(compte);
        } else {
            throw new IllegalArgumentException("Compte not found with id_c: " + id);
        }
    }
    private List<Comptes> users = new ArrayList<>();
    public Comptes getUserByUsername(String email) {
        return users.stream()
                .filter(user -> user.getEmailc().equals(email))
                .findFirst()
                .orElse(null);
    }
    public boolean validatePassword(String email, String password) {
       Comptes user =com_rep.findByEmailc (email);
        return user != null && user.getMdp_c().equals(password);
    }
    public Long getConnectedUserId(HttpSession session) {
        Comptes loggedInUser = (Comptes) session.getAttribute("loggedInUser");
        return (loggedInUser != null) ? loggedInUser.getIdc() : null;
    }

    @Override
    public void ajout(Comptes comptes) {
        com_rep.save(comptes);
    }



}
