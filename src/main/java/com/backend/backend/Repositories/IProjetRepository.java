package com.backend.backend.Repositories;

import com.backend.backend.entities.Projet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProjetRepository extends CrudRepository<Projet,Long> {
    List<Projet> findIdByTitre(String titre);
}
