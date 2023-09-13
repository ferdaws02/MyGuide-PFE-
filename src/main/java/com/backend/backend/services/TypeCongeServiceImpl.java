package com.backend.backend.services;

import com.backend.backend.Repositories.ITypeCongeReposiory;
import com.backend.backend.entities.TypeConge;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TypeCongeServiceImpl implements ITypeCongeService{
    @Autowired
    private ITypeCongeReposiory tc_repo;
    /**
     * @param tc
     */
    @Override
    public void AjoutTC(TypeConge type) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null or empty");
        }

        
        tc_repo.save(type);
    
    }
    @Override
    public void UpdateTC(TypeConge tc) {
        TypeConge typepre= tc_repo.findById(tc.getId_tco()).orElse(null);
        typepre.setType(tc.getType());
        tc_repo.save(typepre);

    }

    /**
     * @return
     */
    @Override
    public Iterable<TypeConge> getALL() {
        return tc_repo.findAll();
    }

}
