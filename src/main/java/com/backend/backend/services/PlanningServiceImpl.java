package com.backend.backend.services;

import com.backend.backend.Repositories.IPlanningRepository;
import com.backend.backend.entities.Planning;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PlanningServiceImpl implements IPlanningServices{
    @Autowired
    private IPlanningRepository plan_repo;
    @Override
    public void AjouterPlaning(Planning P) {
        plan_repo.save(P);
    }
}
