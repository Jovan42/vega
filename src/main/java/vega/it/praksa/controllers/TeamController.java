package vega.it.praksa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vega.it.praksa.model.Employee;
import vega.it.praksa.model.dtos.EmployeeListDto;
import vega.it.praksa.model.dtos.TeamInputDto;
import vega.it.praksa.model.dtos.TeamListDto;
import vega.it.praksa.model.dtos.TeamOutputDto;
import vega.it.praksa.services.TeamService;

@RestController
@RequestMapping("/api/teams")
public class TeamController extends GenericCrudControllerImpl<TeamInputDto, TeamOutputDto, TeamListDto, Long,
        TeamService> {

    @Autowired
    public TeamController(TeamService service) {
        super(service);
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<EmployeeListDto> getEmployees(@PathVariable("id")Long id) {
        return new ResponseEntity<>(service.getEmployees(id), HttpStatus.OK);
    }

    @PostMapping("/{id}/employees/{employeeId}")
    public ResponseEntity<Void> addEmployees(@PathVariable("id")Long id, @PathVariable("employeeId")Long employeeId) {
        service.addEmployee(id, employeeId);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/employees/{employeeId}")
    public ResponseEntity<Void> removeEmployees(@PathVariable("id")Long id, @PathVariable("employeeId")Long employeeId) {
        service.removeEmployee(id, employeeId);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
