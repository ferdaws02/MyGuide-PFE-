package com.backend.backend.Repositories;

import com.backend.backend.entities.Comptes;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IComptesRepository extends JpaRepository<Comptes, Long> {
    Comptes findByIdc (String idc);
    Comptes findByEmailc(String emailc);

}
