package com.vedruna.proyecto1eval.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vedruna.proyecto1eval.dto.ProjectDTO;
import com.vedruna.proyecto1eval.persintance.model.Project;

public interface ProjectServiceI {

    List<ProjectDTO> findByProjectNameContainingIgnoreCase(String word);

    Page<ProjectDTO> showAllProjects(Pageable pageable);

    void save(Project project);

    void updateProject(Integer id, ProjectDTO project);

    void deleteProject(Integer id); 

    ProjectDTO insertProject(ProjectDTO project); 
    
}
