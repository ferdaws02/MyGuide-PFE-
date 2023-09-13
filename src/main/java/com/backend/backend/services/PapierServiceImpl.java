package com.backend.backend.services;

import com.backend.backend.Repositories.IPapierRepository;
import com.backend.backend.entities.Comptes;
import com.backend.backend.entities.Papierdemande;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PapierServiceImpl implements IPapierService {
    @Autowired
    IPapierRepository pap_repo;


    @Override
    public void AjouterPapier(Papierdemande pap) {
        pap_repo.save(pap);

    }
}
