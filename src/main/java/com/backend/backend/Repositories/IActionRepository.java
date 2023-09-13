package com.backend.backend.Repositories;

import com.backend.backend.entities.Actions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IActionRepository extends CrudRepository<Actions, Long> {
}
