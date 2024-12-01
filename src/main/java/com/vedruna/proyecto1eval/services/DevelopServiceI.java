package com.vedruna.proyecto1eval.services;

import com.vedruna.proyecto1eval.persintance.model.Develop;

public interface DevelopServiceI {

    void insertDevelop(Develop develop);

    void save(Develop develop);

    void deleteDevelop(Integer id);

}
