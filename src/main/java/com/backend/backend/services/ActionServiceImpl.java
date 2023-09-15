package com.backend.backend.services;

import com.backend.backend.Repositories.IActionRepository;
import com.backend.backend.Repositories.IServiceManagerRepository;
import com.backend.backend.entities.Actions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ActionServiceImpl implements IActionServices{
    @Autowired
    IActionRepository action_repo;
    @Override
    public void AjouterAction(Actions action) {
        action_repo.save(action);
        
    }
}
