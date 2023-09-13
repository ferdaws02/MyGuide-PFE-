package com.backend.backend.Repositories;

import com.backend.backend.entities.Comptables;
import com.backend.backend.entities.Comptes;
import com.backend.backend.entities.Consultants;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("ComptablesRepository")
public interface IComptablesRepository  extends CrudRepository<Comptes,Long>{
}
