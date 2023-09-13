package com.backend.backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Actions implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id_Action;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_action;
    private String message ;
    @ManyToOne
    @JoinColumn(name = "smAction")
    @JsonProperty
    private Service_Manager smAction;
}
