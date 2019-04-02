package vega.it.praksa.model.dtos;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
public class ProjectDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    private ClientDto client;
    private TeamMemberDto lead;

}
