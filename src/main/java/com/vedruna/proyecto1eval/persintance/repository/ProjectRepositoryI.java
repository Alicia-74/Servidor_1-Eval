package com.vedruna.proyecto1eval.persintance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vedruna.proyecto1eval.dto.ProjectDTO;
import com.vedruna.proyecto1eval.persintance.model.Project;

public interface ProjectRepositoryI extends JpaRepository<Project, Integer>{

   // Búsqueda por coincidencia parcial (contiene la palabra en el nombre), el IgnoreCase, lo busca ya sea minu o mayus
   List<Project> findByProjectNameContainingIgnoreCase(String word);

   boolean existsByProjectName(String projectName);

   void save(ProjectDTO project);
}
