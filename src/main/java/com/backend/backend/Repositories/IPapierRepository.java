package com.backend.backend.Repositories;

import com.backend.backend.entities.Papierdemande;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPapierRepository extends CrudRepository<Papierdemande,Long> {
}
