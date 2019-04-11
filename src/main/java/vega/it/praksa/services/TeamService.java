package vega.it.praksa.services;

import vega.it.praksa.model.dtos.*;

public interface TeamService extends GenericService<TeamInputDto, TeamOutputDto, TeamListDto, Long> {
    EmployeeListDto getEmployees(Long id);
    void addEmployee(Long id, Long employeeId);
    void removeEmployee(Long id, Long employeeId);
}
