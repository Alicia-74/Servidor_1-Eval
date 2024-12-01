package com.vedruna.proyecto1eval.dto;

import java.util.ArrayList;
import java.util.List;

import com.vedruna.proyecto1eval.persintance.model.Project;
import com.vedruna.proyecto1eval.persintance.model.Tecnology;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TecnologyDTO {

    private Integer tecnoId;

    public TecnologyDTO (Tecnology t){
        this.tecnoId = t.getTecnoId();
    }

   
}
