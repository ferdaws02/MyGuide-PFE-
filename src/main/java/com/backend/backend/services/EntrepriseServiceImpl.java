package com.backend.backend.services;

import com.backend.backend.Repositories.IEntrepriseRepository;
import com.backend.backend.entities.Entreprise;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EntrepriseServiceImpl implements IEntrepriseService{
@Autowired
    IEntrepriseRepository entreprise_repo;
public void ajoutEntreprise(Entreprise E){
    entreprise_repo.save(E);
}

    @Override
    public void modifierEntreprise(Entreprise E) {
       Entreprise e= entreprise_repo.findById(E.getId_e()).orElse(null);
        e.setNomentreprise(E.getNomentreprise());
        e.setPays(E.getPays());
        e.setAdresse(E.getAdresse());
        entreprise_repo.save(e);

    }

    /**
     * @param id
     * @param E
     * @return
     */
    @Override
    public Entreprise updateEntreprise(Long id, Entreprise E) {
        Optional<Entreprise> optionalItem = entreprise_repo.findById(id);
        if (optionalItem.isPresent()) {
            Entreprise Entrepri = optionalItem.get();
            Entrepri.setNomentreprise(E.getNomentreprise());
            Entrepri.setPays(E.getPays());
            Entrepri.setAdresse(E.getAdresse());
            return entreprise_repo.save(Entrepri);
        }
        return E;
    }

    /**
     * @return
     */
    @Override
    public ResponseEntity<Iterable<Entreprise> >consulterEntreprise() {
        Iterable<Entreprise> userOptional= entreprise_repo.findAll();
        return new ResponseEntity(userOptional, HttpStatus.OK);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Entreprise getEntreprisebyID(Long id) {
        Entreprise e= entreprise_repo.findById(id).orElse(null);
        return e;
    }


}
