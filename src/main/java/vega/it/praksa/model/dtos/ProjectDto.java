package vega.it.praksa.model.dtos;

import lombok.Data;

import javax.persistence.*;

@Data
public class ProjectDto {
    private Long id;
    private String name;
    private String description;
    private ClientDto client;
    private TeamMemberDto lead;

}
