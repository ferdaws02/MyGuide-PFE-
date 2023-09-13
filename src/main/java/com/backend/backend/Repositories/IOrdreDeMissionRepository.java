package com.backend.backend.Repositories;

import com.backend.backend.entities.OrdreDeMission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdreDeMissionRepository extends CrudRepository<OrdreDeMission, Long> {
}
