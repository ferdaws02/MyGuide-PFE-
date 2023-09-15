package com.backend.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.backend.Repositories.INoteDeFraisRepository;
import com.backend.backend.entities.NoteDeFrais;



@Service
public class NoteDeFraisServiceImpl implements INoteDeFraisService{
    @Autowired
    INoteDeFraisRepository ndf_Repository;
    @Override
    public ResponseEntity<String> Ajoutndf(NoteDeFrais ndf) {
        ndf_Repository.save(ndf);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
}
