package vega.it.praksa.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vega.it.praksa.model.Employee;
import vega.it.praksa.model.dtos.LoginDto;
import vega.it.praksa.services.EmployeeService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    EmployeeService employeeService;

    public AuthController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/login")
    ResponseEntity<LoginDto> login(@RequestBody Employee employee) {
        LoginDto loginDto = new LoginDto(employee.getUsername(),
                employeeService.login(employee.getUsername(), employee.getPassword()));
                return new ResponseEntity<>(loginDto, HttpStatus.OK);
    }
    @PostMapping("/logout")
    ResponseEntity<Void> logout(){
        employeeService.logout();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/loggedIn")
    ResponseEntity<LoginDto> loggedIn (){
        return new ResponseEntity<>(new LoginDto(employeeService.getLoggedIn(), true), HttpStatus.OK);

    }
}
