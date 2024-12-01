package com.vedruna.proyecto1eval.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.proyecto1eval.exceptions.DuplicateEmailException;
import com.vedruna.proyecto1eval.exceptions.DuplicateGithubException;
import com.vedruna.proyecto1eval.exceptions.DuplicateLinkedinException;
import com.vedruna.proyecto1eval.persintance.model.Develop;
import com.vedruna.proyecto1eval.services.DevelopServiceI;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/developers")
@CrossOrigin
public class DevelopController {

    @Autowired
    private DevelopServiceI developMngmnt;

    /**
     * Endpoint para crear un nuevo programador.
     *
     * @param develop Datos del programador a crear.
     * @return ResponseEntity con el programador creado.
     */
    @PostMapping("/insert")
    public ResponseEntity<String> insertDevelop(@RequestBody Develop develop) {
        try {
            developMngmnt.insertDevelop(develop);
            // Respuesta exitosa
            String successMessage = "Desarrollador/a " + develop.getDevName() + " " + develop.getDevSurname() + " creado/a correctamente.";
            return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
        } catch (DuplicateEmailException | DuplicateLinkedinException | DuplicateGithubException e) {
            // Excepciones personalizadas
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // Para errores generales
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }
    

    
    /**
     * Endpoint para eliminar un programador.
     *
     * @param id Identificador del programador a eliminar.
     * @return ResponseEntity con el mensaje de confirmación.
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteDevelop(@PathVariable Integer id) {
        developMngmnt.deleteDevelop(id);
        return ResponseEntity.status(HttpStatus.OK).body("Desarrollador/a " + id + " eliminado/a correctamente.");
    }

}
