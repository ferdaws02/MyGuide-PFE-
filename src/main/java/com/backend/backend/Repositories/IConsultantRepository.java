package com.backend.backend.Repositories;

import com.backend.backend.entities.Comptes;
import com.backend.backend.entities.Consultants;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface IConsultantRepository extends CrudRepository<Comptes,Long>{
    List<Consultants> findByStatusIsNotNull();
//    @Query("Select c FROM Consultants c where c.prenom_c=:prenom ")
//  Consultants getIdByName(@Param("prenom") String prenom);
//    Long findIdByPrenom(String prenom);



}
