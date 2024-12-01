package com.vedruna.proyecto1eval.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.vedruna.proyecto1eval.persintance.model.Develop;
import com.vedruna.proyecto1eval.persintance.model.Project;
import com.vedruna.proyecto1eval.persintance.model.Tecnology;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {

    private Integer projectId;
    private String projectName;
    private String decrip;
    private Date fechaInicio;
    private Date fechaFin;
    private String urlRepo;
    private String urlDemo;
    private String picture;
    private StatusDTO statusdto;
    private List<DevelopDTO> developdto;
    private List<TecnologyDTO> tecnodto;

    public ProjectDTO(Project p) {
        this.projectId = p.getProjectId();
        this.projectName = p.getProjectName();
        this.decrip = p.getDecrip();
        this.urlRepo = p.getUrlRepo();
        this.urlDemo = p.getUrlDemo();
        this.fechaInicio = p.getFechaInicio();
        this.fechaFin = p.getFechaFin();
        this.picture = p.getPicture();
        this.statusdto = new StatusDTO(p.getEstadoProject());
        this.developdto = DevelopDTO(p.getProgramadores());
        this.tecnodto = TecnologyDTO(p.getTecnoUsada());
    }

    private List<DevelopDTO> DevelopDTO(List<Develop> dev) {
        List<DevelopDTO> developdto = new ArrayList<>();
        if (dev != null) {
            for (Develop d : dev) {
                developdto.add(new DevelopDTO(d));
            }
        }
        return developdto;
    }

    private List<TecnologyDTO> TecnologyDTO(List<Tecnology> tecno) {
        List<TecnologyDTO> tecnodto = new ArrayList<>();
        if (tecno != null) {
            for (Tecnology t : tecno) {
                tecnodto.add(new TecnologyDTO(t));
            }
        }
        return tecnodto;
    }

}
