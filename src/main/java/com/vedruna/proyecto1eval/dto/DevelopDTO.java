package com.vedruna.proyecto1eval.dto;

import java.util.ArrayList;
import java.util.List;

import com.vedruna.proyecto1eval.persintance.model.Develop;
import com.vedruna.proyecto1eval.persintance.model.Project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevelopDTO {

    private Integer devId;
    

    public DevelopDTO (Develop d){
        this.devId = d.getDevId();
    }

}
