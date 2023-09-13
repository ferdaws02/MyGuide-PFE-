package com.backend.backend.Repositories;

import com.backend.backend.entities.Demandes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDemandeRepository extends CrudRepository<Demandes, Long> {
}
