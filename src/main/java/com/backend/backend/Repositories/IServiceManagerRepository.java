package com.backend.backend.Repositories;

import com.backend.backend.entities.Comptes;
import com.backend.backend.entities.Consultants;
import com.backend.backend.entities.Service_Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IServiceManagerRepository extends JpaRepository<Comptes, Long> {
    Optional<Service_Manager> findByEmailc(String Email);
}
