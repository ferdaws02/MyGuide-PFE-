package com.backend.backend.Repositories;

import com.backend.backend.entities.Comptes;
import com.backend.backend.entities.Consultants;
import com.backend.backend.entities.Manager_Client;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("ManagerClientRepository")
public interface IManagerClientRepository extends CrudRepository<Comptes,Long>{
}
