package com.backend.backend.controller;


import com.backend.backend.Repositories.IConsultantRepository;
import com.backend.backend.Repositories.IRhRepository;
import com.backend.backend.Repositories.IServiceManagerRepository;
import com.backend.backend.entities.*;
import com.backend.backend.services.*;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import com.cloudinary.Transformation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
@CrossOrigin(origins = "http://localhost:3002")
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/")
public class RestController {
    private final Cloudinary cloudinary;



    @Autowired
    IRhService rhservice;
    @Autowired
    IServiceManagerService  servicemanager;
    @Autowired
    IConsultantService consService;

    @Autowired
    IConsultantRepository consultantRepository;
    @Autowired
    IRhRepository Rh_Repository;
    @Autowired
    IServiceManagerRepository SM_Repository;
    @Autowired
    IActionServices actionservice;
    @Autowired
    FileService fileService;
    public RestController() {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dua2gxyvr",
                "api_key", "687422463155631",
                "api_secret", "8rSjcgEWQ1G7nQ66QvnuV5Y0dJs"
        ));
    }
    @PostMapping(value = "/ajouter_SM", consumes = "multipart/form-data")
    public ResponseEntity<String> ajouterSM(
            @RequestParam("photo_c") MultipartFile file,
            @RequestParam("Id_c") Long Id_c,
            @RequestParam("etat") String etat,
            @RequestParam("adresse_c") String adresse_c,
            @RequestParam("cin_c") String cin_c,
            @RequestParam("ddn_c") Date ddn_c,
            @RequestParam("emailc") String emailc,
            @RequestParam("mdp_c") String mdp_c,
            @RequestParam("nom_c") String nom_c,
            @RequestParam("num_tel_c") String num_tel_c,
            @RequestParam("pole_c") String pole_c,
            @RequestParam("post_c") String post_c,
            @RequestParam("prenom_c") String prenom_c,
            @RequestParam("sexe_c") String sexe_c,
            //@RequestParam("soldecongémaladie") Double soldecongémaladie,
            //@RequestParam("solde_congé_payé") Double solde_congé_payé,
            @RequestParam("roles") String roles)
    {
        try {

            String folderName = "consultant_images"; // Set the folder name
            String publicId = folderName + "/" + Id_c; // Set the public ID (use the consultant's ID)

            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                    "public_id", publicId // Set the public ID for the uploaded image
            ));
            String imageUrl = uploadResult.get("secure_url").toString();
            // Call the saveFile method from the FileService to save the uploaded file
            //fileService.saveFile(file, String.valueOf(Id_c));
            //byte[] fileBytes = file.getBytes();
            Service_Manager SM = new Service_Manager();
            SM.setIdc(Id_c);
            SM.setEtat(etat);
            SM.setAdresse_c(adresse_c);
            SM.setCin_c(cin_c);
            SM.setPhoto_c(file.getBytes());
            SM.setDdn_c(ddn_c);
            SM.setEmailc(emailc);
            SM.setMdp_c(mdp_c);
            SM.setNom_c(nom_c);
            SM.setNum_tel_c(num_tel_c);

            SM.setPrenom_c(prenom_c);
            SM.setSexe_c(sexe_c);
            SM.setRoles(Roles.valueOf(roles));

            SM_Repository.save(SM);
            return ResponseEntity.ok("File uploaded and consultant added successfully!");
        } catch (IOException e) {
            // Handle any exceptions that may occur during file upload
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading the file: " + e.getMessage());
        }
    }

    @PutMapping("/modif_SM")
    public void modifSM(@RequestBody Service_Manager sm){
        servicemanager.ModifSM(sm);
    }

    @GetMapping("/getimage/{idc}")
    public ResponseEntity<byte[]> getConsultantImageById(@PathVariable Long idc) {
        try {
            Optional<Comptes> consultantOptional = consultantRepository.findById(idc);
            if (consultantOptional.isPresent()) {
                Comptes consultant = consultantOptional.get();
                String publicId = "consultant_images/" + consultant.getIdc();
                // Fetch the image URL from Cloudinary
                String imageUrl = cloudinary.url().format("jpg").transformation(
                        new Transformation().width(200).height(200).crop("fill")
                ).generate(publicId);

                // Now, you can fetch the image from the imageUrl using any image loading library or method
                // For example, you can use Spring's RestTemplate to fetch the image bytes:

                // Fetch the image URL from Cloudinary

                // Send the image URL in the response
                return ResponseEntity.ok().body(imageUrl.getBytes());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/ajouter_Consultant", consumes = "multipart/form-data")
    public ResponseEntity<String> ajouterConsultant(
            @RequestParam("photo_c") MultipartFile file,
            @RequestParam("Id_c") Long Id_c,
            @RequestParam("etat") String etat,
            @RequestParam("adresse_c") String adresse_c,
            @RequestParam("cin_c") String cin_c,
            @RequestParam("ddn_c") Date ddn_c,
            @RequestParam("emailc") String emailc,
            @RequestParam("mdp_c") String mdp_c,
            @RequestParam("nom_c") String nom_c,
            @RequestParam("num_tel_c") String num_tel_c,
            @RequestParam("pole_c") String pole_c,
            @RequestParam("post_c") String post_c,
            @RequestParam("prenom_c") String prenom_c,
            @RequestParam("sexe_c") String sexe_c,
            //@RequestParam("soldecongémaladie") Double soldecongémaladie,
            //@RequestParam("solde_congé_payé") Double solde_congé_payé,
            @RequestParam("roles") String roles)
    {
        try {

            String folderName = "consultant_images"; // Set the folder name
            String publicId = folderName + "/" + Id_c; // Set the public ID (use the consultant's ID)

            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                    "public_id", publicId // Set the public ID for the uploaded image
            ));
            String imageUrl = uploadResult.get("secure_url").toString();
            // Call the saveFile method from the FileService to save the uploaded file
            //fileService.saveFile(file, String.valueOf(Id_c));
            //byte[] fileBytes = file.getBytes();
            Consultants consultants = new Consultants();
            consultants.setIdc(Id_c);
            consultants.setEtat(etat);
            consultants.setAdresse_c(adresse_c);
            consultants.setCin_c(cin_c);
            consultants.setPhoto_c(file.getBytes());
            consultants.setDdn_c(ddn_c);
            consultants.setEmailc(emailc);
            consultants.setMdp_c(mdp_c);
            consultants.setNom_c(nom_c);
            consultants.setNum_tel_c(num_tel_c);
            consultants.setPole_c(pole_c);
            consultants.setPost_c(post_c);
            consultants.setPrenom_c(prenom_c);
            consultants.setSexe_c(sexe_c);
            consultants.setRoles(Roles.valueOf(roles));

            consultantRepository.save(consultants);
            return ResponseEntity.ok("File uploaded and consultant added successfully!");
        } catch (IOException e) {
            // Handle any exceptions that may occur during file upload
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading the file: " + e.getMessage());
        }
    }
    @GetMapping("/users")
    public List<Consultants> getUsersWithNonNullStatus() {
        return consService.getUsersWithNonNullStatus();
    }

    @PostMapping("/ajouter_Action")
    public void ajouterAction(@RequestBody Actions action){
        actionservice.AjouterAction(action);
    }
    @PostMapping(value = "/ajouter_RH", consumes = "multipart/form-data")
    public ResponseEntity<String> ajouterRH(
            @RequestParam("photo_c") MultipartFile file,
            @RequestParam("Id_c") Long Id_c,
            @RequestParam("etat") String etat,
            @RequestParam("adresse_c") String adresse_c,
            @RequestParam("cin_c") String cin_c,
            @RequestParam("ddn_c") Date ddn_c,
            @RequestParam("emailc") String emailc,
            @RequestParam("mdp_c") String mdp_c,
            @RequestParam("nom_c") String nom_c,
            @RequestParam("num_tel_c") String num_tel_c,
            @RequestParam("pole_c") String pole_c,
            @RequestParam("post_c") String post_c,
            @RequestParam("prenom_c") String prenom_c,
            @RequestParam("sexe_c") String sexe_c,
            //@RequestParam("soldecongémaladie") Double soldecongémaladie,
            //@RequestParam("solde_congé_payé") Double solde_congé_payé,
            @RequestParam("roles") String roles)
    {
        try {

            String folderName = "consultant_images"; // Set the folder name
            String publicId = folderName + "/" + Id_c; // Set the public ID (use the consultant's ID)

            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                    "public_id", publicId // Set the public ID for the uploaded image
            ));
            String imageUrl = uploadResult.get("secure_url").toString();
            // Call the saveFile method from the FileService to save the uploaded file
            //fileService.saveFile(file, String.valueOf(Id_c));
            //byte[] fileBytes = file.getBytes();
            RH rh = new RH();
            rh.setIdc(Id_c);
            rh.setEtat(etat);
            rh.setAdresse_c(adresse_c);
            rh.setCin_c(cin_c);
            rh.setPhoto_c(file.getBytes());
            rh.setDdn_c(ddn_c);
            rh.setEmailc(emailc);
            rh.setMdp_c(mdp_c);
            rh.setNom_c(nom_c);
            rh.setNum_tel_c(num_tel_c);
            rh.setPrenom_c(prenom_c);
            rh.setSexe_c(sexe_c);
            rh.setRoles(Roles.valueOf(roles));

            Rh_Repository.save(rh);
            return ResponseEntity.ok("File uploaded and consultant added successfully!");
        } catch (IOException e) {
            // Handle any exceptions that may occur during file upload
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading the file: " + e.getMessage());
        }
    }
    @PutMapping("/desactiverCompte/{id}")
    public void desactivercompte(@PathVariable Long id, @ModelAttribute(name="etat") String etat){
        consService.DesactiverCompte(id,etat);
        
    }
    @PutMapping("/modifierMdp/{id}")
    public void modifiermdp(@PathVariable Long id, @ModelAttribute(name="mdp_c") String mdp){
        consService.ModifierMDP(id,mdp);
    }
    @PutMapping("/modifConsultant")
    public void modifConsultant(@RequestBody Consultants consultants){
        consService.Modifuser(consultants);
    }
    @PutMapping("/modifRH")
    public void modifRH(@RequestBody RH rh){
        rhservice.Modifuser(rh);
    }

    @PutMapping("/modifierMdpRh/{id}")
    public void modifiermdpRh(@PathVariable Long id, @ModelAttribute(name="mdp_c") String mdp){
       rhservice.ModifierMDP(id,mdp);
    }
    @GetMapping("/ListeComptes")
    Iterable<Comptes> listerCompte(){return rhservice.getALL();}
    @GetMapping("/CompteById/{id}")
    Optional<Comptes> compteById(@PathVariable Long id){return rhservice.getById(id);}
    @GetMapping("/ListeConsultant")
    Iterable<Comptes> listerRHs()
           {return consService.getALLConsultant();}



}
