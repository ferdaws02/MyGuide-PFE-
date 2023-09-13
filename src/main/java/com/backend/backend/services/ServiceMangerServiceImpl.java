package com.backend.backend.services;

import com.backend.backend.Repositories.IConsultantRepository;
import com.backend.backend.Repositories.IServiceManagerRepository;
//import com.backend.backend.Security.UserDetaileApp;

import com.backend.backend.entities.Comptes;

import com.backend.backend.entities.Service_Manager;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceMangerServiceImpl implements IServiceManagerService{

    @Autowired
    IServiceManagerRepository sm_repo;

    public boolean isIdExists(Long id) {

        Optional<Comptes> myEntityOptional = sm_repo.findById(id);
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
    public ResponseEntity<String> AjouterSM(Service_Manager SM) {

        Long id= SM.getIdc();

        if(id.toString().length()>=4) {
            if (!isIdExists(id)) {
                if(SM.getCin_c().toString().length() == 8){
                    if(isValidEmail(SM.getEmailc())){
                        SM.setMdp_c(SM.getMdp_c());/* SM.setMdp_sm(passwordEncoder.encode(SM.getMdp_sm()));*/
                          sm_repo.save(SM);
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

    @Override
    public void ModifSM(Service_Manager sm) {
        Comptes com = (Service_Manager) sm_repo.findById(sm.getIdc()).orElse(null);
        com.setNom_c(sm.getNom_c());
        com.setPrenom_c(sm.getPrenom_c());
        com.setCin_c(sm.getCin_c());
        com.setDdn_c(sm.getDdn_c());
        com.setAdresse_c(sm.getAdresse_c());
        com.setEmailc(sm.getEmailc());
        com.setNum_tel_c(sm.getNum_tel_c());

        com.setPhoto_c(sm.getPhoto_c());
        com.setSexe_c(sm.getSexe_c());

        sm_repo.save(com);
    }

    /**
     * @param email
     * @return
     */
    @Override
    public Service_Manager getSM(String email) {
        return sm_repo.findByEmailc(email).orElse(null);
    }


    @Override
    public void DesactiverCompte(Long id, String etat) {

    }

    @Override
    public void ModifierMDP(Long id, String mdp) {

    }

    @Override
    public Long getConnectedUserId(HttpSession session) {
        Service_Manager loggedInUser = (Service_Manager) session.getAttribute("loggedInUser");
        System.out.println("loggedInUser");
        return (loggedInUser != null) ? loggedInUser.getIdc() : null;
    }

    @Override
    public void ajout(Comptes comptes) {
        sm_repo.save(comptes);
    }

    @Override
    public void saveFile(MultipartFile file, Long compteId) throws IOException {
// Get the Comptes entity by its "id_c" from the database
        Service_Manager consultant = (Service_Manager) sm_repo.findById(compteId).orElse(null);
        if (consultant == null) {
            throw new IllegalArgumentException("Consultant not found with id_c: " + compteId);
        }

        // Convert the MultipartFile to a byte array
        byte[] fileBytes = file.getBytes();

        // Set the photo_c attribute of the Consultants entity with the byte array
        consultant.setPhoto_c(fileBytes);

        // Save the updated Consultants entity to the database
        sm_repo.save(consultant);
    }


}




