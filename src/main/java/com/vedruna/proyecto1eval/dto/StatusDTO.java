package com.vedruna.proyecto1eval.dto;

import com.vedruna.proyecto1eval.persintance.model.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDTO {

    private Integer statusId;

    public StatusDTO (Status s){
        this.statusId = s.getStatusId();
    }
}
