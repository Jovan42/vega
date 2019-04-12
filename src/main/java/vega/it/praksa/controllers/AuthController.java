package vega.it.praksa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vega.it.praksa.model.Employee;
import vega.it.praksa.model.Work;
import vega.it.praksa.model.dtos.LoginDto;
import vega.it.praksa.repositories.WorkRepository;
import vega.it.praksa.services.EmployeeService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private EmployeeService employeeService;
    @Autowired WorkRepository workRepository;

    public AuthController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/login")
    ResponseEntity<LoginDto> login(@RequestBody Employee employee) {
        LoginDto loginDto =
                new LoginDto(
                        employee.getUsername(),
                        employeeService.login(employee.getUsername(), employee.getPassword()));
        return new ResponseEntity<>(loginDto, HttpStatus.OK);
    }

    @PostMapping("/logout")
    ResponseEntity<Void> logout() {
        employeeService.logout();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/loggedIn")
    ResponseEntity<LoginDto> loggedIn() {
        return new ResponseEntity<>(
                new LoginDto(employeeService.getLoggedIn(), true), HttpStatus.OK);
    }

    @GetMapping("/test")
    List<Long> test() {
        LocalDate start = LocalDate.now();
        start = start.minusDays(7);
        Date date = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());

        System.out.println(date);
        System.out.println(new Date());
        return workRepository.getEmployeesForMailing(date);
    }
}
