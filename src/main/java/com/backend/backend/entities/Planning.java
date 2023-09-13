package com.backend.backend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Planning  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_P;
    @Temporal(TemporalType.DATE)
    private Date date_debut_formation;
    @Temporal(TemporalType.DATE)
    private Date date_fin_formation;

    @OneToOne
    private Formation formation;
    @ManyToOne
    @JoinColumn(name = "rh_p")
    @JsonProperty
    private RH rh_p;
}
