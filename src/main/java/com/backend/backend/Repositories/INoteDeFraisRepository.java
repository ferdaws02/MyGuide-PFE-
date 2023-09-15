package com.backend.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.entities.NoteDeFrais;

@Repository
public interface INoteDeFraisRepository extends JpaRepository<NoteDeFrais,Long> {
    
}
