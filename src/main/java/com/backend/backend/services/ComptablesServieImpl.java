package com.backend.backend.services;

import com.backend.backend.Repositories.IComptablesRepository;
import com.backend.backend.entities.Comptables;
import com.backend.backend.entities.Comptes;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service

public class ComptablesServieImpl  implements IComptablesService {
    @Autowired
    IComptablesRepository repo_com;
    @Override
    public void DesactiverCompte(Long id, String etat) {
        Comptes forma = (Comptes) repo_com.findById(id).orElse(null);
        forma.setEtat(etat);
        repo_com.save(forma);
    }
  @Override
    public void ModifierMDP(Long id, String mdp) {
       Comptables com = (Comptables) repo_com.findById(id).orElse(null);
        com.setMdp_c(mdp);
        repo_com.save(com);
    }
    @Override
    public Long getConnectedUserId(HttpSession session) {
        return null;
    }
@Override
    public void ajout(Comptes comptes) {
        repo_com.save(comptes);}

    public void saveFile(MultipartFile file, Long compteId) throws IOException {
// Get the Comptes entity by its "id_c" from the database
        Comptables consultant = (Comptables) repo_com.findById(compteId).orElse(null);
        if (consultant == null) {
            throw new IllegalArgumentException("Consultant not found with id_c: " + compteId);
        }

        // Convert the MultipartFile to a byte array
        byte[] fileBytes = file.getBytes();

        // Set the photo_c attribute of the Consultants entity with the byte array
        consultant.setPhoto_c(fileBytes);

        // Save the updated Consultants entity to the database
        repo_com.save(consultant);
    }
 public boolean isIdExists(Long id) {
        Optional<Comptes> myEntityOptional = repo_com.findById(id);
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
    public ResponseEntity<String> AjouterComptable(Comptables grh) {

        Long id= grh.getIdc();

        if(id.toString().length()>=4) {
            if (!isIdExists(id)) {
                if(grh.getCin_c().toString().length() == 8){
                    if(isValidEmail(grh.getEmailc())){
                repo_com.save(grh);
                    }else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("vérifier email");
                    }
                }else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le CIN doit etre composé de 8 chiffres");
                }
            }else{ return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("l'id existe déjà");}
        }else {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("l'id doit étre 4 chiffre ou plus");

        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Added");
    }

    @Override
    public void Modifuser(Comptes compte) {
       
        Comptables com = (Comptables) repo_com.findById(compte.getIdc()).orElse(null);

        com.setNom_c(compte.getNom_c());
        com.setPrenom_c(compte.getPrenom_c());
        com.setCin_c(compte.getCin_c());
        com.setDdn_c(compte.getDdn_c());
        com.setAdresse_c(compte.getAdresse_c());
        com.setEmailc(compte.getEmailc());
        com.setNum_tel_c(compte.getNum_tel_c());
        com.setPhoto_c(compte.getPhoto_c());
        com.setSexe_c(compte.getSexe_c());

        repo_com.save(com);
    
    }

    @Override
    public Iterable<Comptes> getALL() {
        Iterable<Comptes> Rh =  repo_com.findAll();
        return Rh;
    }

    @Override
    public Optional<Comptes> getById(Long id) {
        Optional<Comptes> c =repo_com.findById(id);
        return c;
    }


}
