package vega.it.praksa.model.dtos;

import lombok.Data;
import vega.it.praksa.model.enums.Role;
import vega.it.praksa.model.enums.Status;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class TeamMemberDto {
    private Long id;
    private String username;
    private String password;
    private String name;
    private Double hoursPerWeek;
    private Role role;
    private Status status;
}
