package com.vedruna.proyecto1eval.services;

import com.vedruna.proyecto1eval.persintance.model.Tecnology;

public interface TecnologyServiceI {

    void insertTecnology(Tecnology tecnology);

    void save(Tecnology tecnology);

    void deleteTecnology(Integer id);

}
