package vega.it.praksa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vega.it.praksa.model.*;
import vega.it.praksa.model.enums.Role;
import vega.it.praksa.model.enums.Status;
import vega.it.praksa.repositories.*;

import java.util.Date;

@Component
public class InitData implements CommandLineRunner {
    private EmployeeRepository employeeRepository;
    private CountryRepository countryRepository;
    private CategoryRepository categoryRepository;
    private ClientRepository clientRepository;
    private ProjectRepository projectRepository;
    private WorkRepository workRepository;
    private ProjectMemberRepository projectMemberRepository;

    public InitData(
            EmployeeRepository employeeRepository,
            CountryRepository countryRepository,
            CategoryRepository categoryRepository,
            ClientRepository clientRepository,
            ProjectRepository projectRepository,
            WorkRepository workRepository,
            ProjectMemberRepository projectMemberRepository) {
        this.employeeRepository = employeeRepository;
        this.countryRepository = countryRepository;
        this.categoryRepository = categoryRepository;
        this.clientRepository = clientRepository;
        this.projectRepository = projectRepository;
        this.workRepository = workRepository;
        this.projectMemberRepository = projectMemberRepository;
    }

    @Override
    public void run(String... args) {
        Employee employee1 =
                Employee.builder()
                        .email("markomarkovic@gmail.com")
                        .hoursPerWeek(40D)
                        .name("Marko Markovic")
                        .password("password")
                        .role(Role.ADMIN)
                        .status(Status.ACTIVE)
                        .username("marko")
                        .build();
        Employee employee2 =
                Employee.builder()
                        .email("petarpetrovic@gmail.com")
                        .hoursPerWeek(40D)
                        .name("Petar Petrovic")
                        .password("password")
                        .role(Role.ADMIN)
                        .status(Status.ACTIVE)
                        .username("petar")
                        .build();
        Employee employee3 =
                Employee.builder()
                        .email("jovanjovanovic@gmail.com")
                        .hoursPerWeek(40D)
                        .name("Jovan Jovanovic")
                        .password("password")
                        .role(Role.ADMIN)
                        .status(Status.ACTIVE)
                        .username("jovan")
                        .build();
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);

        Category category1 = Category.builder().name("Frontend").build();
        Category category2 = Category.builder().name("Backend").build();

        categoryRepository.save(category1);
        categoryRepository.save(category2);

        Country country1 = Country.builder().name("SRB").build();
        Country country2 = Country.builder().name("USA").build();

        country1 = countryRepository.save(country1);
        countryRepository.save(country2);

        Client client1 =
                Client.builder()
                        .address("59 E. Bayport Ave")
                        .city("Voorhees")
                        .country(country1)
                        .name("Petar Petrovic")
                        .zipCode("08043")
                        .build();

        Client client2 =
                Client.builder()
                        .address("305 Constitution Rd.")
                        .city("Winter Park")
                        .country(country1)
                        .name("Ivan Ivanovic")
                        .zipCode("32792")
                        .build();

        clientRepository.save(client1);
        clientRepository.save(client2);

        Project project1 =
                Project.builder()
                        .client(client1)
                        .lead(employee1)
                        .description("Description")
                        .name("Name")
                        .id(1L)
                        .build();

        Project project2 =
                Project.builder()
                        .client(client1)
                        .lead(employee1)
                        .description("Description")
                        .name("Name")
                        .id(2L)
                        .build();

        projectRepository.save(project1);
        projectRepository.save(project2);

        Work work1 =
                Work.builder()
                        .category(category1)
                        .date(new Date())
                        .description("Work 1")
                        .employee(employee1)
                        .id(1L)
                        .overtime(0D)
                        .time(8D)
                        .project(project1)
                        .build();

        Work work2 =
                Work.builder()
                        .category(category1)
                        .date(new Date())
                        .description("Work 2")
                        .employee(employee1)
                        .id(2L)
                        .overtime(0D)
                        .time(8D)
                        .project(project1)
                        .build();

        workRepository.save(work1);
        workRepository.save(work2);

        ProjectMember projectMember1 =
                ProjectMember.builder()
                        .project(project1)
                        .dailyAllocation(40D)
                        .employee(employee1)
                        .id(1L)
                        .build();
        ProjectMember projectMember2 =
                ProjectMember.builder()
                        .project(project1)
                        .dailyAllocation(40D)
                        .employee(employee3)
                        .id(2L)
                        .build();

        projectMemberRepository.save(projectMember1);
        projectMemberRepository.save(projectMember2);
    }
}
