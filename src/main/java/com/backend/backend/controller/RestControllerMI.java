package com.backend.backend.controller;

import com.backend.backend.Repositories.IManagerInetumRepository;
import com.backend.backend.entities.*;
import com.backend.backend.services.IManagerInetumService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:3002")
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/")
public class RestControllerMI {
    private final Cloudinary cloudinary;
    @Autowired
    IManagerInetumService miService;
    @Autowired
    IManagerInetumRepository repoMI;
    public RestControllerMI() {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dua2gxyvr",
                "api_key", "687422463155631",
                "api_secret", "8rSjcgEWQ1G7nQ66QvnuV5Y0dJs"
        ));
    }
    @PostMapping(value = "/ajouter_MI", consumes = "multipart/form-data")
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
            Manager_Inetum MI = new Manager_Inetum();
            MI.setIdc(Id_c);
            MI.setEtat(etat);
            MI.setAdresse_c(adresse_c);
            MI.setCin_c(cin_c);
            MI.setPhoto_c(file.getBytes());
            MI.setDdn_c(ddn_c);
            MI.setEmailc(emailc);
            MI.setMdp_c(mdp_c);
            MI.setNom_c(nom_c);
            MI.setNum_tel_c(num_tel_c);
            MI.setPole_c(pole_c);
            MI.setPost_c(post_c);
            MI.setPrenom_c(prenom_c);
            MI.setSexe_c(sexe_c);
            MI.setRoles(Roles.valueOf(roles));

            repoMI.save(MI);
            return ResponseEntity.ok("File uploaded and consultant added successfully!");
        } catch (IOException e) {
            // Handle any exceptions that may occur during file upload
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading the file: " + e.getMessage());
        }
    }
    @PutMapping ("/mdfMDPmi/{id_mi}")
    public void modifmdpMI(@PathVariable Long id_mi,@ModelAttribute(name = "mdp")String MDP){miService.ModifierMDP(id_mi,MDP);}

    @PutMapping("/modifMI")
    public void modifMI(@RequestBody Manager_Inetum mi){
        miService.Modifuser(mi);
    }
    @GetMapping("/ListeMIs")
    public List<Comptes> GetAllMIs(){return miService.getALLMI();}
}
