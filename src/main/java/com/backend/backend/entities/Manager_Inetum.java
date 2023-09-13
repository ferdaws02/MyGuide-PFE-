package com.backend.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Manager_Inetum extends Comptes implements Serializable {
    private String post_c;
    private String pole_c;
    private String status_c;
    @ManyToMany
    @JoinTable(
            name = "Affectation",
            joinColumns = @JoinColumn(name = "manager_id"),
            inverseJoinColumns = @JoinColumn(name = "consultant_id"))
    private List<Consultants> consultants = new ArrayList<>();
}
