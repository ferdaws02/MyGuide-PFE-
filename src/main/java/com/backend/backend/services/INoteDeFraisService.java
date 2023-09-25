package com.backend.backend.services;

import org.springframework.http.ResponseEntity;

import com.backend.backend.entities.NoteDeFrais;


public interface INoteDeFraisService {
        public ResponseEntity<String> Ajoutndf(NoteDeFrais ndf);
        public ResponseEntity<Iterable<NoteDeFrais>> getAllNDF() ;
        public ResponseEntity<String> MAJStatusNDF(Long id_ndf, String newEtat) ;
    
}
