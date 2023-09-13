package com.backend.backend.Repositories;

import com.backend.backend.entities.Comptes;
import com.backend.backend.entities.Consultants;
import com.backend.backend.entities.Manager_Inetum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("ManagerInetumRepository")
public interface IManagerInetumRepository  extends CrudRepository<Comptes,Long>{
}
