package com.vedruna.proyecto1eval.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.proyecto1eval.persintance.model.Tecnology;
import com.vedruna.proyecto1eval.services.TecnologyServiceI;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/tecnologies")
@CrossOrigin
public class TecnologyController {

    @Autowired
    private TecnologyServiceI tecnologyMngmnt;

    /**
     * Endpoint para crear una nueva tecnología.
     *
     * @param tecnology Datos de la tecnología a crear.
     * @return ResponseEntity con la tecnología creada.
     */
    @PostMapping("/insert")
    public ResponseEntity<String> insertTecnology(@RequestBody Tecnology tecnology) {
        // Llamar al servicio para insertar la tecnología
        tecnologyMngmnt.insertTecnology(tecnology);
        
        // Crear un mensaje de éxito
        String message = "Tecnología '" + tecnology.getTecnoName() + "' creada correctamente.";

        // Retornar el mensaje con el código de estado 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    /**
     * Endpoint para eliminar una tecnología.
     *
     * @param id Identificador de la tecnología a eliminar.
     * @return ResponseEntity con el mensaje de confirmación.
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteTecnology(@PathVariable Integer id) {
        tecnologyMngmnt.deleteTecnology(id);
        return ResponseEntity.status(HttpStatus.OK).body("Tecnología " + id + " eliminada correctamente.");
    }
}
