package vega.it.praksa.services;

import vega.it.praksa.model.dtos.EmployeeListDto;
import vega.it.praksa.model.dtos.TeamInputDto;
import vega.it.praksa.model.dtos.TeamListDto;
import vega.it.praksa.model.dtos.TeamOutputDto;

public interface TeamService
        extends GenericService<TeamInputDto, TeamOutputDto, TeamListDto, Long> {
    EmployeeListDto getEmployees(Long id);

    void addEmployee(Long id, Long employeeId);

    void removeEmployee(Long id, Long employeeId);
}
