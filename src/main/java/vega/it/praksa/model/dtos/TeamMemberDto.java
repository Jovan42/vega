package vega.it.praksa.model.dtos;

import lombok.Data;
import vega.it.praksa.model.enums.Role;
import vega.it.praksa.model.enums.Status;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TeamMemberDto {
    private Long id;
    @NotBlank
    private String username;
    private String password;
    @NotBlank
    private String name;
    @NotNull
    private Double hoursPerWeek;
    @NotBlank
    private String email;
    @NotNull
    private Role role;
    @NotNull
    private Status status;
}
