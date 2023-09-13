package com.backend.backend.services;


import com.backend.backend.Repositories.IEntrepriseRepository;
import com.backend.backend.Repositories.IManagerClientRepository;
import com.backend.backend.entities.*;
import jakarta.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.backend.backend.services.ServiceMangerServiceImpl.isValidEmail;

@Service
@Slf4j
public class ManagerClientImpl implements IMangerClientService {
    @Autowired
    private IManagerClientRepository MC_Repo;
    @Autowired
    private IEntrepriseRepository E_Repo;

    /**
     * @param id
     * @param etat
     */
    @Override
    public void DesactiverCompte(Long id, String etat) {
        Comptes forma = (Comptes) MC_Repo.findById(id).orElse(null);
        forma.setEtat(etat);
        MC_Repo.save(forma);
    }

    @Override
    public void ModifierMDP(Long id, String mdp) {
        Manager_Inetum mc = (Manager_Inetum) MC_Repo.findById(id).orElse(null);
        mc.setMdp_c(mdp);
        MC_Repo.save(mc);


    }

    @Override

    public Long getConnectedUserId(HttpSession session) {
        return null;
    }

    @Override
    public void ajout(Comptes comptes) {
        MC_Repo.save(comptes);}

    public void saveFile(MultipartFile file, Long compteId) throws IOException {
// Get the Comptes entity by its "id_c" from the database
        Manager_Client consultant = (Manager_Client) MC_Repo.findById(compteId).orElse(null);
        if (consultant == null) {
            throw new IllegalArgumentException("Consultant not found with id_c: " + compteId);
        }

        // Convert the MultipartFile to a byte array
        byte[] fileBytes = file.getBytes();

        // Set the photo_c attribute of the Consultants entity with the byte array
        consultant.setPhoto_c(fileBytes);

        // Save the updated Consultants entity to the database
        MC_Repo.save(consultant);
    }
    @Override
    public  List<Comptes> getMC() {
        Iterable<Comptes> consultants =  MC_Repo.findAll();
        List<Comptes> ConsultantComptes = new ArrayList<>();

        for (Comptes compte : consultants) {
            if (compte.getRoles()== Roles.Manager_Client) {
                ConsultantComptes.add(compte);

            }
        }
        return ConsultantComptes;
    }


    @Override
    public void Modifuser(Manager_Client compte) {

        Manager_Client com = (Manager_Client) MC_Repo.findById(compte.getIdc()).orElse(null);

        com.setNom_c(compte.getNom_c());
        com.setPrenom_c(compte.getPrenom_c());
        com.setCin_c(compte.getCin_c());
        com.setDdn_c(compte.getDdn_c());
        com.setAdresse_c(compte.getAdresse_c());
        com.setEmailc(compte.getEmailc());
        com.setNum_tel_c(compte.getNum_tel_c());
        com.setPole_c(compte.getPole_c());
        com.setPost_c(compte.getPost_c());
        com.setPhoto_c(compte.getPhoto_c());
        com.setSexe_c(compte.getSexe_c());
         Entreprise entreprise =compte.getEntreprise();
         if (entreprise!= null){
             com.setEntreprise(entreprise);
         }
        MC_Repo.save(com);

    }

    public boolean isIdExists(Long id) {
        Optional<Comptes> myEntityOptional = MC_Repo.findById(id);
        return myEntityOptional.isPresent();
    }

    /**
     * @param mc
     * @return
     */
    @Override
    public ResponseEntity<String> AjouterMC(Manager_Client mc) {

        Long id = mc.getIdc();

        if (id.toString().length() >= 4) {
            if (!isIdExists(id)) {
                if (mc.getCin_c().toString().length() == 8) {
                    if (isValidEmail(mc.getEmailc())) {
                        MC_Repo.save(mc);
                    } else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("vérifier email");
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le CIN doit etre composé de 8 chiffres");
                }
            } else {
                return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("l'id existe déjà");
            }
        } else {
            return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.BAD_REQUEST).body("l'id doit étre 4 chiffre ou plus");

        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Added");
    }

    /**
     * @param mc
     * @return
     */
    @Override
    public ResponseEntity<String> UpdateAll(Manager_Client mc,String NomEntreprise) {

        Long id = mc.getIdc();

        Manager_Client oldMC = (Manager_Client) MC_Repo.findById(id).orElse(null);
        if (isValidEmail(mc.getEmailc())) {
            if (mc.getCin_c().toString().length() == 8) {
                List<Entreprise> entreprise = E_Repo.findIdByNomentreprise(NomEntreprise);
                if(entreprise.isEmpty()){
                    return ResponseEntity.status(404).body("le projet n'existe pas");
                }else {
                    for (Entreprise E : entreprise) {
                        Long ide = E.getId_e();
                        MC_Repo.delete(oldMC);
                        mc.setEntreprise(E);
                        MC_Repo.save(mc);
                    }
                }
            } else {
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le CIN doit etre composé de 8 chiffres");
            }
        }else { return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("vérifier email");
      }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Updated");
    }
    @Override
    public  List<Comptes> getALLMC() {
        Iterable<Comptes> mcs =  MC_Repo.findAll();
        List<Comptes> McComptes = new ArrayList<>();

        for (Comptes compte : mcs) {
            if (compte.getRoles()== Roles.Manager_Client) {
                McComptes.add(compte);

            }
        }
        return McComptes;
    }
}

