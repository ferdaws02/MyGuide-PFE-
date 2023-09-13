package com.backend.backend.Repositories;

import com.backend.backend.entities.Entreprise;
import com.backend.backend.entities.Projet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEntrepriseRepository extends CrudRepository<Entreprise,Long> {
    List<Entreprise> findIdByNomentreprise( String nom);
    Optional<Entreprise> findById(Long id);

}
