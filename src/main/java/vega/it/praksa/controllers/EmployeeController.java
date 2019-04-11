package vega.it.praksa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vega.it.praksa.model.dtos.EmployeeOutputDto;
import vega.it.praksa.model.dtos.EmployeeInputDto;
import vega.it.praksa.model.dtos.EmployeeListDto;
import vega.it.praksa.services.EmployeeService;

@RestController
@RequestMapping("/api/team-members")
public class EmployeeController extends GenericCrudControllerImpl<EmployeeInputDto, EmployeeOutputDto, EmployeeListDto, Long,
        EmployeeService> {
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        super(employeeService);
    }
}
