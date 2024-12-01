package com.vedruna.proyecto1eval.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vedruna.proyecto1eval.exceptions.DuplicateResourceException;
import com.vedruna.proyecto1eval.exceptions.ResourceNotFoundException;
import com.vedruna.proyecto1eval.exceptions.ResourceProcessingException;
import com.vedruna.proyecto1eval.persintance.model.Project;
import com.vedruna.proyecto1eval.persintance.model.Tecnology;
import com.vedruna.proyecto1eval.persintance.repository.ProjectRepositoryI;
import com.vedruna.proyecto1eval.persintance.repository.TecnologyRepositoryI;

@Service
public class TecnologyServiceImpl implements TecnologyServiceI{
    
    @Autowired
    TecnologyRepositoryI tecnologyRepo;

     @Autowired
    ProjectRepositoryI projectRepo;

    @Override
    public void insertTecnology(Tecnology tecnology) {
        try {

            // Validar duplicados
            if (tecnologyRepo.existsByTecnoName(tecnology.getTecnoName())) {
                throw new DuplicateResourceException("El nombre de la tecnología ya existe: " + tecnology.getTecnoName());
            }


            // Crear el objeto Tecnology
            Tecnology newTecnology = new Tecnology();
            newTecnology.setTecnoId(tecnology.getTecnoId());
            newTecnology.setTecnoName(tecnology.getTecnoName());

            // Guardar la tecnología
            tecnologyRepo.save(newTecnology);

        } catch (DuplicateResourceException e) {
            // Relanzar la excepción personalizada
            throw e;
        } catch (Exception e) {
            // Capturar cualquier otra excepción no prevista
            throw new ResourceProcessingException("Error al insertar tecnología: " + e.getMessage());
        }
    }


    //método guardar
    @Override
    public void save(Tecnology tecnology) {
        tecnologyRepo.save(tecnology);
    }

    //método borrar tecnología   
    @Override
    public void deleteTecnology(Integer id) {
        // Buscar la tecnología que queremos eliminar
        Tecnology technology = tecnologyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La tecnología con id " + id + " no existe."));

        // Desvincular la tecnología de los proyectos en los que está asociada
        technology.getProyectoUsandoTecnología().forEach(project -> {
            project.getTecnoUsada().remove(technology); // Elimina la tecnología de la lista de tecnologías del proyecto
            projectRepo.save(project); // Guardar el proyecto con la relación actualizada
        });

        // Eliminar la tecnología de la base de datos
        tecnologyRepo.deleteById(id);
    }

}
