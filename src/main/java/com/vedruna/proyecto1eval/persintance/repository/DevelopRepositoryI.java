package com.vedruna.proyecto1eval.persintance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vedruna.proyecto1eval.persintance.model.Develop;


public interface DevelopRepositoryI extends JpaRepository<Develop, Integer>{

    boolean existsByEmail(String email);

    boolean existsByLinkedin(String linkedin);

    boolean existsByGithub(String github);

}
