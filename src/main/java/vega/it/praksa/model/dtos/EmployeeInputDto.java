package vega.it.praksa.model.dtos;

import lombok.*;
import vega.it.praksa.model.enums.Role;
import vega.it.praksa.model.enums.Status;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeInputDto {
    private Long id;
    @NotBlank private String username;
    private String password;
    @NotBlank private String name;
    @NotNull private Double hoursPerWeek;
    @NotBlank private String email;
    @NotNull private Role role;
    @NotNull private Status status;
}
