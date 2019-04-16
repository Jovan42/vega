package vega.it.praksa.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamOutputDto {
    private Long id;
    private String name;
    private EmployeeOutputDto teamLeader;
    private List<EmployeeOutputDto> employees;
}
