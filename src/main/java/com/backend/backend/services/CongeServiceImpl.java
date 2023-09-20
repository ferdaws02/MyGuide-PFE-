package com.backend.backend.services;

import com.backend.backend.Repositories.ICongeRepository;
import com.backend.backend.Repositories.IConsultantRepository;
import com.backend.backend.Repositories.ITypeCongeReposiory;
import com.backend.backend.dto.EtatCongeDTO;
import com.backend.backend.entities.*;
import com.backend.backend.entities.DTOs.CongeDTO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CongeServiceImpl implements ICongeService {
    @Autowired
    ICongeRepository co_Repo;
    @Autowired
    ITypeCongeReposiory Tco_Repo;
    @Autowired
    IConsultantRepository con_Repo;

    public static boolean isDateAfterToday(LocalDate date) {
        LocalDate today = LocalDate.now();
        return date.isAfter(today);
    }


    public static int compareDates(LocalDate date1, LocalDate date2) {
        return date1.compareTo(date2);
    }

    public static long calculateDaysBetween(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }
    /**
     * @param coDto de type Conge
     */
    @Override
    public ResponseEntity<String> AjoutConge(CongeDTO coDto, TypeConge tco,Consultants con) {
        Conge co = new Conge();
        co.setEtat(coDto.getEtat());
        co.setDfconge(coDto.getDfconge());
        co.setDdconge(coDto.getDdconge());
        co.setTypeConge(coDto.getTypeConge());
        co.setEtat(EtatConge.validation_client);
        co.setConsultant(con);
        if ((isDateAfterToday(co.getDdconge())) && (isDateAfterToday(co.getDfconge()))) {
            if (compareDates(co.getDdconge(), co.getDfconge()) < 0) {
                Long NBr= calculateDaysBetween(co.getDdconge(),co.getDfconge());
                if(NBr<22) {
                    List<TypeConge> Types = (List<TypeConge>) Tco_Repo.findALLTypeCongeByType(tco.getType());
                    if (Types.isEmpty()) {
                        return new ResponseEntity(HttpStatus.NOT_FOUND);
                    } else {
                        for (TypeConge type : Types) {
                            Long idtco = type.getId_tco();
                            TypeConge t = Tco_Repo.findById(idtco).orElse(null);
                            co.setTypeConge(t);
                            co_Repo.save(co);
                        }
                        return ResponseEntity.ok().body("Créer");

                    }
                }else{return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);}

            } else {
                return ResponseEntity.status(400).body("vérifier les dates ");

            }
        } else {
            return ResponseEntity.status(400).body("les date doit étre supérieur à la date actuelle  ");
        }
    }

    /**
     * @param coDto
     * @return
     */
    @Override
    public ResponseEntity<String> MettreAJourCo(CongeDTO coDto, String tco) {
        Conge co = new Conge();
        co.setId_co(coDto.getId_co());
        co.setEtat(coDto.getEtat());
        co.setDfconge(coDto.getDfconge());
        co.setDdconge(coDto.getDdconge());
        co.setTypeConge(coDto.getTypeConge());
        co.setConsultant(coDto.getConsultant());
        if ((isDateAfterToday(co.getDdconge())) && (isDateAfterToday(co.getDfconge()))) {
            if (compareDates(co.getDdconge(), co.getDfconge()) < 0) {
                Conge Co = co_Repo.findById(co.getId_co()).orElse(null);
                List<TypeConge> Types = Tco_Repo.findALLTypeCongeByType(tco);
                if (Types.isEmpty()) {
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
                } else {
                    for (TypeConge type : Types) {
                        Long idtco = type.getId_tco();
                        TypeConge t = Tco_Repo.findById(idtco).orElse(null);
                        co.setTypeConge(t);
                        assert Co != null;
                        Co.setTypeConge(co.getTypeConge());
                        Co.setDdconge(co.getDdconge());
                        Co.setDfconge(co.getDfconge());
                        Co.setConsultant(co.getConsultant());
                        co_Repo.save(Co);
                    }
                }return new ResponseEntity(HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param id
     * @param Eco
     * @return
     */
    @Transactional
    @Override
    public ResponseEntity<String> MettreAJourStatusCo(Long id, EtatConge Eco) {
        Conge Co = co_Repo.findById(id).orElse(null);
        if(Co!=null){
            EtatConge conge=EtatConge.valueOf(String.valueOf(Eco));
        Co.setEtat(conge);
        co_Repo.save(Co);
        return new ResponseEntity(HttpStatus.OK);
    }else{
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @return
     */
    @Override
    public List<CongeDTO> getALL() {
        List<Conge> conges = (List<Conge>) co_Repo.findAll();
        return conges.stream()
            .map(this::mapToCongeDTO)
            .collect(Collectors.toList());
    }
    
    private CongeDTO mapToCongeDTO(Conge conge) {
        CongeDTO dto = new CongeDTO();
        dto.setId_co(conge.getId_co());
        dto.setDdconge(conge.getDdconge());
        dto.setDfconge(conge.getDfconge());
        dto.setEtat(conge.getEtat());
        dto.setConsultant(conge.getConsultant());
        dto.setTypeConge(conge.getTypeConge());
        return dto;
    }
    /**
     * @param consultantId
     * @return
     */
    @Override
    public List<Conge> getCongesByConsultantId(Long consultantId) {
        Consultants co = (Consultants) con_Repo.findById(consultantId).orElse(null);
        return co_Repo.findByConsultant(co);
    }

    /**
     * @param Con_id
     * @return
     */
    @Override
    public Long SoldeDeCongeRestant(Long Con_id) {
        Long RestJ= 22L;
        Long TJC= 0L;
        List<Conge> ListCo=getCongesByConsultantId(Con_id);
        for(Conge conge:ListCo){
           LocalDate DD= conge.getDdconge();
           LocalDate DF= conge.getDfconge();
           Long NbrJ=calculateDaysBetween(DD,DF);
            TJC=TJC+NbrJ;
        }
        RestJ=RestJ-TJC;

        return RestJ;
    }


}
