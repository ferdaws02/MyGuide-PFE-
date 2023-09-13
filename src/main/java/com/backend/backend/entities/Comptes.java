package com.backend.backend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.NaturalId;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public  class  Comptes
//        implements UserDetails
{
    @Id

    @Column(name="id_c")
    private Long idc;

    private String nom_c;

    @Column(name = "prenom_c")
    private String prenom_c;
    private String cin_c;
    private String adresse_c;
    //    private String email_c;
//    private String mdp_c;
    @Temporal(TemporalType.DATE)
    private Date ddn_c;
    @Lob
    @Column (name = "photo_c", columnDefinition = "longblob")
    private byte[] photo_c;

    private String sexe_c;

    private String num_tel_c;
    private  String etat;
    @NaturalId(mutable = true)
    private String emailc;
    @Enumerated(EnumType.STRING)
    private Roles roles;
    private String mdp_c;

    @ManyToOne
    @JoinColumn(name = "ServiceManager")
    @JsonProperty
    private Service_Manager serviceManager;


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//   return List.of(new SimpleGrantedAuthority(roles.name()));
//    }
//
//    @Override
//    public String getPassword() {
//        return mdp_c;
//    }
//
//    @Override
//    public String getUsername() {
//        return emailc;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }


}
