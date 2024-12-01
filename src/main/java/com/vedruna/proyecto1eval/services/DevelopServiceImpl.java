package com.vedruna.proyecto1eval.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vedruna.proyecto1eval.exceptions.DuplicateEmailException;
import com.vedruna.proyecto1eval.exceptions.DuplicateGithubException;
import com.vedruna.proyecto1eval.exceptions.DuplicateLinkedinException;
import com.vedruna.proyecto1eval.exceptions.ResourceNotFoundException;
import com.vedruna.proyecto1eval.exceptions.ResourceProcessingException;
import com.vedruna.proyecto1eval.persintance.model.Develop;
import com.vedruna.proyecto1eval.persintance.repository.DevelopRepositoryI;
import com.vedruna.proyecto1eval.persintance.repository.ProjectRepositoryI;

@Service
public class DevelopServiceImpl implements DevelopServiceI{
    
    @Autowired
    DevelopRepositoryI developRepo;

    @Autowired
    ProjectRepositoryI projectRepo;

    @Override
    public void insertDevelop(Develop develop) {
        try {

            // Validar duplicados
            if (developRepo.existsByEmail(develop.getEmail())) {
                System.out.println("Email duplicado: " + develop.getEmail()); // Para depuración
                throw new DuplicateEmailException("El email ya existe: " + develop.getEmail());
            }
            if (developRepo.existsByLinkedin(develop.getLinkedin())) {
                System.out.println("Linkedin duplicado: " + develop.getLinkedin()); // Para depuración
                throw new DuplicateLinkedinException("El Linkedin ya existe: " + develop.getLinkedin());
            }
            if (developRepo.existsByGithub(develop.getGithub())) {
                System.out.println("Github duplicado: " + develop.getGithub()); // Para depuración
                throw new DuplicateGithubException("El Github ya existe: " + develop.getGithub());
            }

            // Crear el objeto Develop
            Develop newDevelop = new Develop();
            newDevelop.setDevId(develop.getDevId());
            newDevelop.setDevName(develop.getDevName());
            newDevelop.setDevSurname(develop.getDevSurname());
            newDevelop.setEmail(develop.getEmail());
            newDevelop.setLinkedin(develop.getLinkedin());
            newDevelop.setGithub(develop.getGithub());

            // Guardar el desarrollador
            developRepo.save(newDevelop);

        } catch (DuplicateEmailException | DuplicateLinkedinException | DuplicateGithubException e) {
            // Relanzar las excepciones personalizadas para que las maneje el GlobalExceptionHandler
            throw e;
        } catch (Exception e) {
            // Si la base de datos lanza una excepción (por ejemplo, duplicado de una restricción única), manejarla aquí
            if (e.getCause() instanceof java.sql.SQLException) {
                java.sql.SQLException sqlException = (java.sql.SQLException) e.getCause();
                if (sqlException.getMessage().contains("Duplicate entry")) {
                    throw new ResourceProcessingException("El email ya existe: " + develop.getEmail());
                }
            }
            // Capturar cualquier otra excepción y lanzar una excepción genérica
            throw new ResourceProcessingException("Error al insertar desarrollador: " + e.getMessage());
        
        }
    }


    //método guardar
    @Override
    public void save(Develop develop) {
        developRepo.save(develop);
    }


    //método borrar programador
   @Override
    public void deleteDevelop(Integer id) {
        // Buscar el desarrollador que queremos eliminar
        Develop developer = developRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El desarrollador con id " + id + " no existe."));

        // Desvincular el desarrollador de los proyectos en los que está asociado
        developer.getProyectoEnDesarrollo().forEach(project -> {
            project.getProgramadores().remove(developer); // Elimina al desarrollador de la lista de programadores del proyecto
            projectRepo.save(project); // Guardar el proyecto con la relación actualizada
        });

        // Eliminar el desarrollador de la base de datos
        developRepo.deleteById(id);
    }

}
