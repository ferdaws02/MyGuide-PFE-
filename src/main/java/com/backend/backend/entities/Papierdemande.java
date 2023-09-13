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
public class Papierdemande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_papier;
    private String url_papier;
    @Temporal(TemporalType.DATE)
    private Date uploaddate;
    @ManyToOne
    @JoinColumn(name = "Papier_demande")
    @JsonProperty
    private Demandes Papier;
}
