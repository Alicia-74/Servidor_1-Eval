package com.vedruna.proyecto1eval.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vedruna.proyecto1eval.dto.DevelopDTO;
import com.vedruna.proyecto1eval.dto.ProjectDTO;
import com.vedruna.proyecto1eval.dto.TecnologyDTO;
import com.vedruna.proyecto1eval.exceptions.DuplicateResourceException;
import com.vedruna.proyecto1eval.exceptions.ResourceNotFoundException;
import com.vedruna.proyecto1eval.exceptions.ResourceProcessingException;
import com.vedruna.proyecto1eval.persintance.model.Develop;
import com.vedruna.proyecto1eval.persintance.model.Project;
import com.vedruna.proyecto1eval.persintance.model.Status;
import com.vedruna.proyecto1eval.persintance.model.Tecnology;
import com.vedruna.proyecto1eval.persintance.repository.DevelopRepositoryI;
import com.vedruna.proyecto1eval.persintance.repository.ProjectRepositoryI;
import com.vedruna.proyecto1eval.persintance.repository.StatusRepositoryI;
import com.vedruna.proyecto1eval.persintance.repository.TecnologyRepositoryI;

@Service
public class ProjectServiceImpl implements ProjectServiceI{
    
    @Autowired
    ProjectRepositoryI projectRepo;

    @Autowired
    private StatusRepositoryI statusRepo;

    @Autowired
    private DevelopRepositoryI developRepo;

    @Autowired
    private TecnologyRepositoryI tecnologyRepo;

    @Override
    public List<ProjectDTO> findByProjectNameContainingIgnoreCase(String word) {
        // Obtiene los proyectos que contienen la palabra en el nombre
        List<Project> projects = projectRepo.findByProjectNameContainingIgnoreCase(word);

         // Convierte los proyectos a DTO usando un bucle for
         List<ProjectDTO> projectDtos = new ArrayList<>();
         for (Project project : projects) {
             ProjectDTO dto = new ProjectDTO(project); // Usa el constructor existente
             projectDtos.add(dto);
         }
     
         return projectDtos; // Retorna la lista de DTOs
    }

    @Override
    public Page<ProjectDTO> showAllProjects(Pageable pageable) {
        // Obtener los proyectos paginados desde el repositorio
        Page<Project> projects = projectRepo.findAll(pageable);

        // Convertir cada Project en un ProjectDTO
        return projects.map(ProjectDTO::new);
    }

    
    @Override
    public void save(Project project) {
        projectRepo.save(project);
    }

    @Override
    public ProjectDTO insertProject(ProjectDTO projectDTO) {
        try {
            // Validar si el proyecto ya existe
            if (projectRepo.existsByProjectName(projectDTO.getProjectName())) {
                throw new DuplicateResourceException("El nombre del proyecto ya existe: " + projectDTO.getProjectName());
            }

            // Crear el objeto Project
            Project project = new Project();
            project.setProjectName(projectDTO.getProjectName());
            project.setDecrip(projectDTO.getDecrip());
            project.setFechaInicio(projectDTO.getFechaInicio());
            project.setFechaFin(projectDTO.getFechaFin());
            project.setUrlRepo(projectDTO.getUrlRepo());
            project.setUrlDemo(projectDTO.getUrlDemo());
            project.setPicture(projectDTO.getPicture());

            // Validar y asignar el estado
            if (projectDTO.getStatusdto() == null || projectDTO.getStatusdto().getStatusId() == null) {
                throw new ResourceProcessingException("El estado del proyecto no puede ser nulo.");
            }
            Status status = statusRepo.findById(projectDTO.getStatusdto().getStatusId())
                    .orElseThrow(() -> new ResourceNotFoundException("Estado con ID " + projectDTO.getStatusdto().getStatusId() + " no encontrado."));
            project.setEstadoProject(status);

            // Validar y asignar los desarrolladores
            List<Develop> developers = new ArrayList<>();
            if (projectDTO.getDevelopdto() != null) {
                for (DevelopDTO devDTO : projectDTO.getDevelopdto()) {
                    if (devDTO.getDevId() == null) {
                        throw new ResourceProcessingException("El ID del desarrollador no puede ser nulo.");
                    }
                    Develop developer = developRepo.findById(devDTO.getDevId())
                            .orElseThrow(() -> new ResourceNotFoundException("Desarrollador con ID " + devDTO.getDevId() + " no encontrado."));
                    developers.add(developer);
                }
            }
            project.setProgramadores(developers);

            // Validar y asignar las tecnologías
            List<Tecnology> technologies = new ArrayList<>();
            if (projectDTO.getTecnodto() != null) {
                for (TecnologyDTO techDTO : projectDTO.getTecnodto()) {
                    if (techDTO.getTecnoId() == null) {
                        throw new ResourceProcessingException("El ID de la tecnología no puede ser nulo.");
                    }
                    Tecnology technology = tecnologyRepo.findById(techDTO.getTecnoId())
                            .orElseThrow(() -> new ResourceNotFoundException("Tecnología con ID " + techDTO.getTecnoId() + " no encontrada."));
                    technologies.add(technology);
                }
            }
            project.setTecnoUsada(technologies);

            // Guardar el proyecto en la base de datos
            Project savedProject = projectRepo.save(project);
            return new ProjectDTO(savedProject);

        } catch (DuplicateResourceException e) {
            throw e; // Relanzar excepciones personalizadas
        } catch (Exception ex) {
            throw new ResourceProcessingException("Error al procesar el proyecto: " + ex.getMessage());
        }
    }

    @Override
    public void updateProject(Integer id, ProjectDTO projectDTO) {
        // Buscar el proyecto existente
        Project existingProject = projectRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El proyecto con id " + id + " no existe."));

        // Actualizar campos básicos
        existingProject.setProjectName(projectDTO.getProjectName());
        existingProject.setDecrip(projectDTO.getDecrip());
        existingProject.setFechaInicio(projectDTO.getFechaInicio());
        existingProject.setFechaFin(projectDTO.getFechaFin());
        existingProject.setUrlRepo(projectDTO.getUrlRepo());
        existingProject.setUrlDemo(projectDTO.getUrlDemo());
        existingProject.setPicture(projectDTO.getPicture());

        // Actualizar estado
        if (projectDTO.getStatusdto() != null) {
            Status status = statusRepo.findById(projectDTO.getStatusdto().getStatusId())
                    .orElseThrow(() -> new ResourceNotFoundException("Estado con ID " + projectDTO.getStatusdto().getStatusId() + " no encontrado."));
            existingProject.setEstadoProject(status);
        }

        // Actualizar desarrolladores
        if (projectDTO.getDevelopdto() != null && !projectDTO.getDevelopdto().isEmpty()) {
            List<Develop> developers = new ArrayList<>();
            for (DevelopDTO devDTO : projectDTO.getDevelopdto()) {
                Develop developer = developRepo.findById(devDTO.getDevId())
                        .orElseThrow(() -> new ResourceNotFoundException("Desarrollador con ID " + devDTO.getDevId() + " no encontrado."));
                developers.add(developer);
            }
            existingProject.setProgramadores(developers);
        }

        // Actualizar tecnologías
        if (projectDTO.getTecnodto() != null && !projectDTO.getTecnodto().isEmpty()) {
            List<Tecnology> technologies = new ArrayList<>();
            for (TecnologyDTO techDTO : projectDTO.getTecnodto()) {
                Tecnology technology = tecnologyRepo.findById(techDTO.getTecnoId())
                        .orElseThrow(() -> new ResourceNotFoundException("Tecnología con ID " + techDTO.getTecnoId() + " no encontrada."));
                technologies.add(technology);
            }
            existingProject.setTecnoUsada(technologies);
        }

        // Guardar los cambios
        projectRepo.save(existingProject);
    }


    //método borrar proyecto
    @Override
    public void deleteProject(Integer id) {
        // Buscar el proyecto que queremos eliminar
        Project project = projectRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El proyecto con id " + id + " no existe."));
    
        // Desvincular los desarrolladores y tecnologías del proyecto
        project.getProgramadores().clear();  // Elimina las asociaciones con los desarrolladores
        project.getTecnoUsada().clear();     // Elimina las asociaciones con las tecnologías
    
        // Guardar el proyecto con las asociaciones desvinculadas (sin eliminar las entidades relacionadas)
        projectRepo.save(project);
    
        // Ahora eliminar el proyecto
        projectRepo.deleteById(id);
    }
    
    
}
