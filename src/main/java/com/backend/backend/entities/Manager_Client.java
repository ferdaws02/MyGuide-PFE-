package com.backend.backend.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Manager_Client extends Comptes implements Serializable {

    private String post_c;
    private String pole_c;
    private String status_c;
    @ManyToOne
    @JoinColumn(name="entreprise")
    private Entreprise entreprise;
    @OneToMany(mappedBy = "managerclient",cascade = CascadeType.ALL)
    private List<Consultants> Consultants=new ArrayList<>();
}
