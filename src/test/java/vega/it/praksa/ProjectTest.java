package vega.it.praksa;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import vega.it.praksa.model.*;
import vega.it.praksa.model.enums.Role;
import vega.it.praksa.model.enums.Status;
import vega.it.praksa.repositories.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjectTest {
    private MockMvc mockMvc;
    private ClientRepository clientRepository;
    private CountryRepository countryRepository;
    private EmployeeRepository employeeRepository;
    private ProjectRepository projectRepository;

    @Before
    public void setUp() {
        Country country = new Country(1L, "a");
        countryRepository.save(country);

        Client client1 = new Client(1L, "a", "a", "a", "a", country);
        clientRepository.save(client1);

        Employee employee1 = new Employee(1l, "a", "a", "a", 40d
                , "a", Role.ADMIN, Status.ACTIVE);
        employeeRepository.save(employee1);

        Project project = new Project(1L, "a", "a", client1, employee1);
        projectRepository.save(project);

    }
    @Test
    public void findAll() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findById() throws Exception {

        mockMvc.perform(get("http://localhost:8080/api/projects/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void add() throws Exception {
        mockMvc.perform(post("http://localhost:8080/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":2,\"name\":\"c\",\"description\":\"c\",\"client\":1,\"lead\":1}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void edit() throws Exception {

        mockMvc.perform(put("http://localhost:8080/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":2,\"name\":\"editC\",\"description\":\"c\",\"client\":1,\"lead\":1}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void findByName() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/projects/by-name?name=a")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void findByFirstLetter() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/projects/first-letter?letter=a")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    //TODO pitaj
    //@Test
    public void remove() throws Exception {
        mockMvc.perform(delete("http://localhost:8080/api/projects/2"))
                .andExpect(status().isOk());
    }

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Autowired
    public void setCountryRepository(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    public void setProjectRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
}
