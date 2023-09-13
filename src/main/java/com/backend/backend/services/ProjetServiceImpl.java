package com.backend.backend.services;

import com.backend.backend.Repositories.IConsultantRepository;
import com.backend.backend.Repositories.IEntrepriseRepository;
import com.backend.backend.Repositories.IProjetRepository;
import com.backend.backend.entities.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProjetServiceImpl implements IProjetService {

    @Autowired
    private IConsultantRepository consultantRepository;
    @Autowired
    private IEntrepriseRepository entrepriseRepository;
    @Autowired
    private IProjetRepository projectRepository;

    @Override
    public ResponseEntity<String> ajoutProjet(Projet P,String E) {
        Projet co = new Projet();
        co.setTitre(P.getTitre());
        co.setDescription(P.getDescription());

        List<Entreprise> Entreprises = entrepriseRepository.findIdByNomentreprise(E);
        if (Entreprises.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            for (Entreprise entreprise: Entreprises) {
                Long idE = entreprise.getId_e();
                Entreprise t = entrepriseRepository.findById(idE).orElse(null);
               co.setEntreprise(t);
                projectRepository.save(co);
            }
        }
        return ResponseEntity.ok().body("Créer");

    }

    /**
     * @param P
     */
    @Override
    public void modifierProjet(Projet P) {
        Projet p = projectRepository.findById(P.getId_p()).orElse(null);
        p.setTitre(P.getTitre());
        p.setEntreprise(P.getEntreprise());
        p.setDescription(P.getDescription());
        projectRepository.save(p);


    }

    /**
     * @return
     */
    @Override
    public Iterable<Projet> getALL() {
        Iterable<Projet> projets =  projectRepository.findAll();
        return projets;
    }

    public boolean isIdExists(Long id) {
        Optional<Projet> myEntityOptional = projectRepository.findById(id);
        return myEntityOptional.isPresent();
    }
}
