package vega.it.praksa.model.dtos;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProjectDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private ClientDto client;
    @NotNull
    private TeamMemberDto lead;

}
