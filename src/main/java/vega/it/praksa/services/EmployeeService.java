package vega.it.praksa.services;

import vega.it.praksa.model.dtos.EmployeeInputDto;
import vega.it.praksa.model.dtos.EmployeeListDto;
import vega.it.praksa.model.dtos.EmployeeOutputDto;

public interface EmployeeService
        extends GenericService<EmployeeInputDto, EmployeeOutputDto, EmployeeListDto, Long> {
    Boolean changePassword(Long id, String newPassword);

    EmployeeOutputDto get(String username);

    Boolean login(String username, String password);

    void logout();

    String getLoggedIn();

    EmployeeListDto getAllWithoutProject();

    EmployeeListDto getAllWithoutTeam();
}
