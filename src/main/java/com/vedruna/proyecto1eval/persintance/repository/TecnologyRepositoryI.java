package com.vedruna.proyecto1eval.persintance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vedruna.proyecto1eval.persintance.model.Tecnology;

public interface TecnologyRepositoryI extends JpaRepository<Tecnology, Integer>{

    boolean existsByTecnoName(String tecnoName);
  
}
