package vega.it.praksa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vega.it.praksa.model.dtos.EmployeeInputDto;
import vega.it.praksa.model.dtos.EmployeeListDto;
import vega.it.praksa.model.dtos.EmployeeOutputDto;
import vega.it.praksa.services.EmployeeService;

@RestController
@RequestMapping("/api/team-members")
public class EmployeeController
        extends GenericCrudControllerImpl<
                EmployeeInputDto, EmployeeOutputDto, EmployeeListDto, Long, EmployeeService> {
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        super(employeeService);
    }

    @GetMapping("/no-project")
    public ResponseEntity<EmployeeListDto> getAllWithoutProject() {
        return new ResponseEntity<>(service.getAllWithoutProject(), HttpStatus.OK);
    }

    @GetMapping("/no-team")
    public ResponseEntity<EmployeeListDto> getAllWithoutTeam() {
        return new ResponseEntity<>(service.getAllWithoutTeam(), HttpStatus.OK);
    }
}
