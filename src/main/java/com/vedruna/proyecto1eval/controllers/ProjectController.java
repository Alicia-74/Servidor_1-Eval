package com.vedruna.proyecto1eval.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.proyecto1eval.dto.ProjectDTO;
import com.vedruna.proyecto1eval.exceptions.ResourceNotFoundException;
import com.vedruna.proyecto1eval.exceptions.ResourceProcessingException;
import com.vedruna.proyecto1eval.persintance.model.Project;
import com.vedruna.proyecto1eval.services.ProjectServiceI;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/projects")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectServiceI projectMngmnt;

    /**
     * Endpoint para obtener todos los proyectos paginados.
     * Los parámetros de paginación y orden se gestionan automáticamente con Pageable.
     *
     * @param pageable Objeto que contiene la configuración de paginación y orden.
     * @return ResponseEntity con la página de proyectos.
     */
    @GetMapping
    public ResponseEntity<Page<ProjectDTO>> getAllProjects(Pageable pageable) {
        return ResponseEntity.ok(projectMngmnt.showAllProjects(pageable));
    }

    /**
     * Endpoint para obtener un proyecto cuyo nombre contenga una palabra clave.
     *
     * @param word Palabra clave para buscar en el nombre del proyecto.
     * @return ResponseEntity con el proyecto correspondiente.
     */
      @GetMapping("/{word}")
      public ResponseEntity<List<ProjectDTO>> getProjectsByWord(@PathVariable String word) {
          List<ProjectDTO> projects = projectMngmnt.findByProjectNameContainingIgnoreCase(word);
  
          if (projects.isEmpty()) {
              return ResponseEntity.noContent().build(); // Retorna 204 si no hay resultados
          }
  
          return ResponseEntity.ok(projects); // Retorna 200 con los resultados
      }

    /**
     * Endpoint para crear un nuevo proyecto.
     *
     * @param project Datos del proyecto a crear.
     * @return ResponseEntity con el proyecto creado y el mensaje.
     */
    @PostMapping("/insert")
    public ResponseEntity<String> insertProject(@RequestBody ProjectDTO pdto) {
        projectMngmnt.insertProject(pdto); // Llamamos al método insertProject
        return ResponseEntity.status(HttpStatus.CREATED).body("El Proyecto " + pdto.getProjectName() + " se ha creado correctamente");
    }
    

    /**
     * Endpoint para actualizar un proyecto existente.
     *
     * @param id      Identificador del proyecto a actualizar.
     * @param project Nuevos datos del proyecto.
     * @return ResponseEntity con el proyecto actualizado y un mensaje.
     */
    @PutMapping("edit/{id}")
    public ResponseEntity<String> updateProject(@PathVariable Integer id, @RequestBody ProjectDTO projectDTO) {
        try {
            // Llamar al servicio para actualizar el proyecto
            projectMngmnt.updateProject(id, projectDTO);

            // Respuesta de éxito
            return ResponseEntity.status(HttpStatus.OK).body("Proyecto " + id + " actualizado correctamente.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (ResourceProcessingException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error inesperado: " + e.getMessage());
        }
    }



    /**
     * Endpoint para eliminar un proyecto.
     *
     * @param id Identificador del proyecto a eliminar.
     * @return ResponseEntity con el mensaje de confirmación.
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Integer id) {
        projectMngmnt.deleteProject(id);
        return ResponseEntity.status(HttpStatus.OK).body("Proyecto " + id + " eliminado correctamente.");
    }

}
