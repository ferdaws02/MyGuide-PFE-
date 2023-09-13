package com.backend.backend.entities.DTOs;

import com.backend.backend.entities.Consultants;
import com.backend.backend.entities.Manager_Inetum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AffectationCmiDTO {
    private Consultants consultants;
    private Manager_Inetum MIs;
}
