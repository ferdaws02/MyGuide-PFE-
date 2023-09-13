package com.backend.backend.services;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.backend.backend.entities.Comptes;
import com.backend.backend.Repositories.IComptesRepository;
import com.cloudinary.Cloudinary;

import java.io.IOException;
import java.util.Map;
@Service
public class FileService {
    private final IComptesRepository comptesRepository;
    private final Cloudinary cloudinary;

    public FileService(IComptesRepository comptesRepository) {
        this.comptesRepository = comptesRepository;
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "delhkteum",
                "api_key", "695279818156185",
                "api_secret", "Ov2O4mKlXeIAV5lGlILUoMrWpp0"
        ));
    }

    // Rest of the code...

    public void saveFile(MultipartFile file, String Idc) throws IOException {
        // Get the Comptes entity by its ID from the database4

     Comptes compte = comptesRepository.findByIdc(Idc);
        if (compte == null) {
            throw new IllegalArgumentException("Compte not found with id_c: " + Idc);
        }

        // Convert the file data to a byte array and store it in the Comptes entity
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String imageUrl = uploadResult.get("secure_url").toString();

        compte.setPhoto_c(imageUrl.getBytes());


        // Save the updated Comptes entity to the database
        comptesRepository.save(compte);
    }

}
