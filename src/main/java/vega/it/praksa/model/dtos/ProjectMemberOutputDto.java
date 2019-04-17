package vega.it.praksa.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vega.it.praksa.model.Employee;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectMemberOutputDto {
    private Long id;

    @NotNull private Employee employee;
    @NotNull private String project;
    @NotNull private Double dailyAllocation;
}
