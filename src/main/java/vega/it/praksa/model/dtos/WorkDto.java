package vega.it.praksa.model.dtos;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
public class WorkDto {
    private Long id;
    private ProjectDto project;
    private CategoryDto category;
    private String description;
    private Double time;
    private Double overtime;
    @Temporal(TemporalType.DATE)
    private Date date = new Date();
}
