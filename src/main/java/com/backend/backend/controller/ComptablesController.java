package com.backend.backend.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.backend.backend.Repositories.IComptablesRepository;
import com.backend.backend.entities.Comptables;
import com.backend.backend.entities.Entreprise;
import com.backend.backend.entities.Roles;
import com.backend.backend.services.IComptablesService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@RestController
@RequestMapping("/Comptables")
public class ComptablesController {
     @Autowired
    IComptablesService comptables_Service;
    private final Cloudinary cloudinary;

    @Autowired
    IComptablesRepository repoComptable;

    public ComptablesController() {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dua2gxyvr",
                "api_key", "687422463155631",
                "api_secret", "8rSjcgEWQ1G7nQ66QvnuV5Y0dJs"
        ));
    }
    
    @PutMapping("/mdfMDP/{id_mc}")
    public void modifierMdpCpt(@PathVariable Long id_mc, @ModelAttribute(name = "mdp") String MDP) {
        comptables_Service.ModifierMDP(id_mc, MDP);
    }
    // @PutMapping("/modifCpt")
    // public void modifcpt(@RequestBody Comptables mc){
    //     comptables_Service.Modifuser(mc);
    // }

    @PostMapping(value = "/ajouter_cpt", consumes = "multipart/form-data")
    public ResponseEntity<String> ajouterMIt(
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
            // @RequestParam("pole_c") String pole_c,
            // @RequestParam("post_c") String post_c,
            @RequestParam("prenom_c") String prenom_c,
            @RequestParam("sexe_c") String sexe_c,
            //@RequestParam("soldecongémaladie") Double soldecongémaladie,
            //@RequestParam("solde_congé_payé") Double solde_congé_payé,
            @RequestParam("roles") String roles)
            // @RequestParam("entreprise") Entreprise entreorise)
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
            Comptables MC = new Comptables();
            MC.setIdc(Id_c);
            MC.setEtat(etat);
            MC.setAdresse_c(adresse_c);
            MC.setCin_c(cin_c);
            MC.setPhoto_c(file.getBytes());
            MC.setDdn_c(ddn_c);
            MC.setEmailc(emailc);
            MC.setMdp_c(mdp_c);
            MC.setNom_c(nom_c);
            MC.setNum_tel_c(num_tel_c);
            // MC.setPole_c(pole_c);
            // MC.setPost_c(post_c);
            MC.setPrenom_c(prenom_c);
            MC.setSexe_c(sexe_c);
            MC.setRoles(Roles.valueOf(roles));
            // MC.setEntreprise(entreorise);
            repoComptable.save(MC);
            return ResponseEntity.ok("File uploaded and consultant added successfully!");
        } catch (IOException e) {
            // Handle any exceptions that may occur during file upload
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading the file: " + e.getMessage());
        }
    }
    @PutMapping("/modifCpt")
    public void modifCpt(@RequestBody Comptables rh){
        comptables_Service.Modifuser(rh);
    }

    @PutMapping("/modifierMdpCpt/{id}")
    public void modifiermdpRh(@PathVariable Long id, @ModelAttribute(name="mdp_c") String mdp){
       comptables_Service.ModifierMDP(id,mdp);
    }


}