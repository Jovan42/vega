package vega.it.praksa.model.dtos;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class WorkDto {
    private Long id;
    @NotNull
    private ProjectDto project;
    @NotNull
    private CategoryDto category;
    @NotBlank
    private String description;
    @NotNull
    private Double time;
    @NotNull
    private Double overtime;
    @NotNull
    private Date date;
}
