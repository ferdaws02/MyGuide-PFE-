package com.backend.backend.services;

import com.backend.backend.Repositories.IConsultantRepository;
import com.backend.backend.Repositories.IManagerInetumRepository;

import com.backend.backend.entities.*;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpSession;

import com.backend.backend.entities.Comptes;
import com.backend.backend.entities.Manager_Inetum;
import com.backend.backend.entities.Roles;

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
import java.util.regex.Pattern;

@Service
@Slf4j
public class ManagerInetumSevice implements IManagerInetumService {
    @Autowired
    IManagerInetumRepository mi_repo;
    @Autowired
    IConsultantRepository consultantRepository;
    public boolean isIdExists(Long id) {
        Optional<Comptes> myEntityOptional = mi_repo.findById(id);
        return myEntityOptional.isPresent();
    }
    public static boolean isValidEmail(String email) {
        if(StringUtils.isEmpty(email)) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
    @Override
    public ResponseEntity<String> AjouterMI(Manager_Inetum mi) {

        Long id= mi.getIdc();

        if(id.toString().length()>=4) {
            if (!isIdExists(id)) {
                if(mi.getCin_c().toString().length() == 8){
                    if(isValidEmail(mi.getEmailc())){
                mi_repo.save(mi);
                }else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("vérifier email");
                    }
                }else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le CIN doit etre composé de 8 chiffres");
                }
            }else{ return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("l'id existe déjà");}
        }else {
            return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.BAD_REQUEST).body("l'id doit étre 4 chiffre ou plus");

        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Added");
    }

    public void saveFile(MultipartFile file, Long compteId) throws IOException {
// Get the Comptes entity by its "id_c" from the database
        Manager_Inetum consultant = (Manager_Inetum) mi_repo.findById(compteId).orElse(null);
        if (consultant == null) {
            throw new IllegalArgumentException("Consultant not found with id_c: " + compteId);
        }

        // Convert the MultipartFile to a byte array
        byte[] fileBytes = file.getBytes();

        // Set the photo_c attribute of the Consultants entity with the byte array
        consultant.setPhoto_c(fileBytes);

        // Save the updated Consultants entity to the database
        mi_repo.save(consultant);
    }


    @Override
    public void DesactiverCompte(Long id, String etat) {
        Comptes forma = (Comptes) mi_repo.findById(id).orElse(null);
        forma.setEtat(etat);
        mi_repo.save(forma);

    }

    @Override
    public void ModifierMDP(Long id, String mdp) {
        Manager_Inetum mi= (Manager_Inetum) mi_repo.findById(id).orElse(null);
        mi.setMdp_c(mdp);
        mi_repo.save(mi);


    }

    @Override

    public Long getConnectedUserId(HttpSession session) {
       Service_Manager loggedInUser = (Service_Manager) session.getAttribute("loggedInUser");
        System.out.println("loggedInUser");
        return (loggedInUser != null) ? loggedInUser.getIdc() : null;
    }

    @Override
    public void ajout(Comptes comptes) {
        mi_repo.save(comptes);
    }

    @Override
    public void Modifuser(Manager_Inetum compte) {

        Manager_Inetum com = (Manager_Inetum) mi_repo.findById(compte.getIdc()).orElse(null);

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
        com.setRoles(compte.getRoles());

        mi_repo.save(com);
    }

    @Override
    public List<Comptes> getALLMI() {
        Iterable<Comptes> mis =  mi_repo.findAll();
        List<Comptes> MiComptes = new ArrayList<>();

        for (Comptes compte : mis) {
            if (compte.getRoles()== Roles.Manager_Inetum){
                MiComptes.add(compte);

            }
        }
        return MiComptes;
    }
}