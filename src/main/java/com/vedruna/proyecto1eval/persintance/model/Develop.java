package com.vedruna.proyecto1eval.persintance.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name="developers")
public class Develop {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dev_id")
    private Integer devId;

    @Column(name="dev_name")
    private String devName;

    @Column(name="dev_surname")
    private String devSurname;

    @Column(name="email")
    private String email;

    @Column(name="linkedin_url")
    private String linkedin;

    @Column(name="github_url")
    private String github;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy="programadores")
    private List<Project> proyectoEnDesarrollo;

}