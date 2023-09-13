package com.backend.backend.Repositories;

import com.backend.backend.entities.Conge;
import com.backend.backend.entities.Consultants;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICongeRepository extends CrudRepository<Conge,Long> {
    List<Conge> findByConsultant(Consultants consultant);
}
