package com.backend.backend.Repositories;

import com.backend.backend.entities.Affectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAffectationRepository extends JpaRepository<Affectation,Long> {
}
