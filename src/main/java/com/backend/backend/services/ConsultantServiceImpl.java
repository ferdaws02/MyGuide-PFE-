package com.backend.backend.services;

import com.backend.backend.Repositories.IConsultantRepository;
import com.backend.backend.Repositories.IManagerClientRepository;
import com.backend.backend.Repositories.IManagerInetumRepository;
import com.backend.backend.Repositories.IProjetRepository;
import com.backend.backend.entities.*;
import com.backend.backend.entities.DTOs.AffectationCmcDTO;
import com.backend.backend.entities.DTOs.AffectationCmiDTO;
import com.backend.backend.entities.DTOs.AffectationDTO;
import io.micrometer.common.util.StringUtils;

import jakarta.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;


@Service
@Slf4j
public class ConsultantServiceImpl implements IConsultantService {
    @Autowired
    IConsultantRepository cons_Repo;
    @Autowired
    IProjetRepository pro_repo;
    @Autowired
    IManagerClientRepository mc_repo;
    @Autowired
    ProjetServiceImpl preSer;
    @Autowired
    ManagerClientImpl mcSer;
    @Autowired
    IManagerInetumRepository mi_repo;

    public boolean isIdExists(Long id) {
        Optional<Comptes> myEntityOptional = cons_Repo.findById(id);
        return myEntityOptional.isPresent();
    }

    public static boolean isValidEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    public static boolean isDateBeforeToday(LocalDate date) {
        LocalDate today = LocalDate.now();
        return date.isBefore(today);
    }
    public static boolean isDateAfterToday(LocalDate date) {
        LocalDate today = LocalDate.now();
        return date.isAfter(today);
    }
    public static boolean isBeforeOrEqual(LocalDate date1) {
        LocalDate today = LocalDate.now();
        return date1.isBefore(today) || date1.isEqual(today);
    }
    public static boolean isAfterOrEqual(LocalDate date1) {
        LocalDate today = LocalDate.now();
        return date1.isAfter(today) || date1.isEqual(today);
    }



    public static int compareDates(LocalDate date1, LocalDate date2) {
        return date1.compareTo(date2);
    }

    @Override
    public ResponseEntity<String> AjouterConsultant(Consultants consultant) {

        Long id_c = consultant.getIdc();

        if (id_c.toString().length() >= 4) {
            if (!isIdExists(id_c)) {
                if (consultant.getCin_c().toString().length() == 8) {
                    if (isValidEmail(consultant.getEmailc())) {
                        cons_Repo.save(consultant);
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("l'id doit étre 4 chiffre ou plus");

        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Added");
    }

    @Override
    public void DesactiverCompte(Long id, String etat) {
        Comptes forma = (Comptes) cons_Repo.findById(id).orElse(null);
        forma.setEtat(etat);
        cons_Repo.save(forma);
    }

    @Override
    public ResponseEntity<String> AffectationCP(AffectationDTO aff) {

        Long id_c = aff.getConsultant().getIdc();

//        Consultants c= (Consultants) cons_Repo.findById(id_c).orElse(null);
        if (isIdExists(id_c)) {
            Consultants com = (Consultants) cons_Repo.findById(id_c).orElse(null);
            Long id_P = aff.getProjet().getId_p();
           // Projet projets = pro_repo.findById(id_P).orElse(null);
           // System.out.println("Liste des Projet" + projets);


                Projet Pr = pro_repo.findById(id_P).orElse(null);
                com.setProjet(Pr);
                LocalDate DDp = aff.getDdaff_projet();
                LocalDate Dfp = aff.getDfaff_projet();
                com.setDdaff_projet(DDp);
                com.setDfaff_projet(Dfp);
                Long Mc=aff.getEntreprise().getIdc();
            if (Mc!= null) {
                com.setManagerclient(aff.getEntreprise());
            }
                if (DDp.isAfter(Dfp)) {
                    return ResponseEntity.badRequest().build();
                } else if (isDateBeforeToday(DDp) && isDateBeforeToday(Dfp)) {
                    com.setStatus("Clôturé");
                    cons_Repo.save(com);

                } else if (isBeforeOrEqual(DDp) && isAfterOrEqual(Dfp)) {
                    com.setStatus("En cours");
                    cons_Repo.save(com);

                } else if (isDateAfterToday(DDp) && isDateAfterToday(Dfp)) {
                    com.setStatus("En attente");
                    cons_Repo.save(com);

                } else {
                    return ResponseEntity.badRequest().build();
                }


            } else {
                return ResponseEntity.status(404).body("le projet n'existe pas");
            }



        return ResponseEntity.status(200).body("Affecter");
    }



    /**
     * @param
     * @param cmcDTO
     * @return
     */
    @Override
    public ResponseEntity<String> AffectationCMC(AffectationCmcDTO cmcDTO) {

        Consultants c = (Consultants) cons_Repo.findById(cmcDTO.getConsultant().getIdc()).orElse(null);

        c.setManagerclient(cmcDTO.getManagerclient());
        cons_Repo.save(c);
        return ResponseEntity.ok().build();

    }

    @Override
    public ResponseEntity<String> AffectationCMI(AffectationCmiDTO aff) {

        Consultants c= (Consultants) cons_Repo.findById(aff.getConsultants().getIdc()).orElse(null);
        Manager_Inetum mi = (Manager_Inetum) mi_repo.findById(aff.getMIs().getIdc()).orElse(null);


        return null;
    }

    @Override
    public  List<Comptes> getALLConsultant() {
        Iterable<Comptes> consultants =  cons_Repo.findAll();
        List<Comptes> ConsultantComptes = new ArrayList<>();

        for (Comptes compte : consultants) {
            if (compte.getRoles()== Roles.Consultant) {
                ConsultantComptes.add(compte);

            }
        }
        return ConsultantComptes;
    }


    @Override
    public void ModifierMDP(Long id, String mdp) {
       Consultants com = (Consultants) cons_Repo.findById(id).orElse(null);
        com.setMdp_c(mdp);
        cons_Repo.save(com);

    }



    @Override

    public Long getConnectedUserId(HttpSession session) {
        Consultants loggedInUser = (Consultants) session.getAttribute("loggedInUser");
        System.out.println("loggedInUser");
        return (loggedInUser != null) ? loggedInUser.getIdc() : null;
    }

    @Override
    public void ajout(Comptes comptes) {
        cons_Repo.save(comptes);
    }


    public void saveFile(MultipartFile file, Long compteId) throws IOException {
// Get the Comptes entity by its "id_c" from the database
        Consultants consultant = (Consultants) cons_Repo.findById(compteId).orElse(null);
        if (consultant == null) {
            throw new IllegalArgumentException("Consultant not found with id_c: " + compteId);
        }

        // Convert the MultipartFile to a byte array
        byte[] fileBytes = file.getBytes();

        // Set the photo_c attribute of the Consultants entity with the byte array
        consultant.setPhoto_c(fileBytes);

        // Save the updated Consultants entity to the database
        cons_Repo.save(consultant);
    }

    @Override
    public void Modifuser(Consultants compte) {
        Consultants com = (Consultants) cons_Repo.findById(compte.getIdc()).orElse(null);
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

        cons_Repo.save(com);

    }
    public List<Consultants> getUsersWithNonNullStatus() {
        return cons_Repo.findByStatusIsNotNull();
    }

}
