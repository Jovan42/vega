package vega.it.praksa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vega.it.praksa.model.dtos.*;
import vega.it.praksa.model.enums.Role;
import vega.it.praksa.model.enums.Status;
import vega.it.praksa.services.*;

public class InitData implements CommandLineRunner {
    private EmployeeService employeeService;
    private CountryService countryService;
    private CategoryService categoryService;
    private ProjectService projectService;
    private ClientService clientService;

    public InitData(
            EmployeeService employeeService,
            CountryService countryService,
            CategoryService categoryService,
            ProjectService projectService,
            ClientService clientService) {
        this.employeeService = employeeService;
        this.countryService = countryService;
        this.categoryService = categoryService;
        this.projectService = projectService;
        this.clientService = clientService;
    }

    @Override
    public void run(String... args) throws Exception {
        addEmployees();
        addCountries();
        addCategories();
        //addProjects();
    }

    private void addEmployees() {
        EmployeeInputDto employee1 =
                EmployeeInputDto.builder()
                        .email("markomarkovic@gmail.com")
                        .hoursPerWeek(40D)
                        .name("Marko Markovic")
                        .password("password")
                        .role(Role.ADMIN)
                        .status(Status.ACTIVE)
                        .username("marko")
                        .build();
        EmployeeInputDto employee2 =
                EmployeeInputDto.builder()
                        .email("petarpetrovic@gmail.com")
                        .hoursPerWeek(40D)
                        .name("Petar Petrovic")
                        .password("password")
                        .role(Role.ADMIN)
                        .status(Status.ACTIVE)
                        .username("petar")
                        .build();
        EmployeeInputDto employee3 =
                EmployeeInputDto.builder()
                        .email("jovanjovanovic@gmail.com")
                        .hoursPerWeek(40D)
                        .name("Jovan Jovanovic")
                        .password("password")
                        .role(Role.ADMIN)
                        .status(Status.ACTIVE)
                        .username("jovan")
                        .build();
        employeeService.add(employee1);
        employeeService.add(employee2);
        employeeService.add(employee3);
    }

    private void addCountries() {
        CountryDto country1 = CountryDto.builder().name("SRB").build();
        CountryDto country2 = CountryDto.builder().name("USA").build();

        countryService.add(country1);
        countryService.add(country2);
    }

    private void addCategories() {
        CategoryDto country1 = CategoryDto.builder().name("Frontend").build();
        CategoryDto country2 = CategoryDto.builder().name("Backend").build();

        categoryService.add(country1);
        categoryService.add(country2);
    }
}

    //SELECT employee_id , SUM (time)  FROM WORK w WHERE w.date <   '2019-12-4' AND w.date > '2019-5-4'  GROUP BY employee_id HAVING SUM(time) < 40;