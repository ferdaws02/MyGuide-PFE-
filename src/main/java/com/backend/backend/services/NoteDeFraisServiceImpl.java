package com.backend.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.backend.Repositories.INoteDeFraisRepository;
import com.backend.backend.entities.Conge;
import com.backend.backend.entities.EtatConge;
import com.backend.backend.entities.NoteDeFrais;
import com.backend.backend.entities.OrdreDeMission;

import jakarta.transaction.Transactional;




@Service
public class NoteDeFraisServiceImpl implements INoteDeFraisService{
    @Autowired
    INoteDeFraisRepository ndf_Repository;
    @Override
    public ResponseEntity<String> Ajoutndf(NoteDeFrais ndf) {
        ndf_Repository.save(ndf);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @Override
    public ResponseEntity<Iterable<NoteDeFrais>> getAllNDF() {
          Iterable<NoteDeFrais> ndf =   ndf_Repository.findAll();
        return new ResponseEntity(ndf, HttpStatus.OK);
    }
    @Transactional
    @Override
    public ResponseEntity<String> MAJStatusNDF(Long id_ndf, String newEtat) {    
            NoteDeFrais ndf = ndf_Repository.findById(id_ndf).orElse(null);
      

    ndf.setStatusNdf("Valider");
        ndf_Repository.save(ndf);
       return new ResponseEntity<>(HttpStatus.OK);
        }
    
    

}
