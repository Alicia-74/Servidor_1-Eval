package com.vedruna.proyecto1eval.persintance.model;

import java.sql.Date;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name="projects")
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="project_id")
    private Integer projectId;

    @Column(name="project_name")
    private String projectName;

    @Column(name="description")
    private String decrip;

    @Column(name="start_date")
    private Date fechaInicio;

    @Column(name="end_date")
    private Date fechaFin;

    @Column(name="repository_url")
    private String urlRepo;

    @Column(name="demo_url")
    private String urlDemo;

    @Column(name="picture")
    private String picture;

    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="status_status_id", referencedColumnName = "status_id")
    private Status estadoProject;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})  // No REMOVE
    @JoinTable(name="developers_worked_on_projects", joinColumns={@JoinColumn(name="projects_project_id")}, inverseJoinColumns={@JoinColumn(name="developers_dev_id")})
    private List<Develop> programadores;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})  // No REMOVE
    @JoinTable(name="technologies_used_in_projects", joinColumns={@JoinColumn(name="projects_project_id")}, inverseJoinColumns={@JoinColumn(name="technologies_tech_id")})
    private List<Tecnology> tecnoUsada;
}
