package com.backend.backend.Repositories;

import com.backend.backend.entities.Formation;
import com.backend.backend.entities.Manager_Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFormationRepository extends CrudRepository<Formation, Long> {
}
