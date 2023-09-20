package com.backend.backend.Repositories;

import com.backend.backend.entities.TypeConge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITypeCongeReposiory extends CrudRepository<TypeConge,Long> {
    List<TypeConge>findALLTypeCongeByType(String Type);
    List<TypeConge>findTypeCongeByType(String Type);
}
