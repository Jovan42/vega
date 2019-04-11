package vega.it.praksa.model.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProjectOutputDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private ClientOutputDto client;
    @NotNull
    private EmployeeInputDto lead;

}
