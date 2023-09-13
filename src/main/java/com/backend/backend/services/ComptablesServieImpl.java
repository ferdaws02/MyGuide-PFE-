package com.backend.backend.services;

import com.backend.backend.entities.Comptes;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class ComptablesServieImpl  implements IComptablesService {
    @Override
    public void DesactiverCompte(Long id, String etat) {

    }

    @Override
    public void ModifierMDP(Long id, String mdp) {

    }

    @Override
    public Long getConnectedUserId(HttpSession session) {
        return null;
    }

    @Override
    public void ajout(Comptes comptes) {

    }

    @Override
    public void saveFile(MultipartFile file, Long compteId) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveFile'");
    }


}
