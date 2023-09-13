package com.backend.backend.services;

import com.backend.backend.entities.TypeConge;

import java.util.List;

public interface ITypeCongeService {
 public void AjoutTC(TypeConge tc);

    void UpdateTC(TypeConge tc);
    Iterable<TypeConge>getALL();
}
