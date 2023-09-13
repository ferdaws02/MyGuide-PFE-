package com.backend.backend.Repositories;

import com.backend.backend.entities.Manager_Inetum;
import com.backend.backend.entities.Planning;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlanningRepository extends CrudRepository<Planning, Long> {

}
