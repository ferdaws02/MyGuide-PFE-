package com.backend.backend.entities.DTOs;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Service_ManagerDTO {
    @Id
    @Column(name="id_sm")
    private Long id_sm;
    private String email_sm;

    private String mdp_sm;
    private String token;
}
