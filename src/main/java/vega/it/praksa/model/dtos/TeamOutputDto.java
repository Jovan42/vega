package vega.it.praksa.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vega.it.praksa.model.Employee;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamOutputDto {
    private Long id;
    private String name;
    private List<EmployeeOutputDto> employees;
}
