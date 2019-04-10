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

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WorkTest {
    private MockMvc mockMvc;
    private ClientRepository clientRepository;
    private CountryRepository countryRepository;
    private TeamMemberRepository teamMemberRepository;
    private ProjectRepository projectRepository;
    private CategoryRepository categoryRepository;
    private WorkRepository workRepository;

    @Before
    public void setUp() {
        Country country = new Country(1L, "a");
        countryRepository.save(country);

        Client client1 = new Client(1L, "a", "a", "a", "a", country);
        clientRepository.save(client1);

        TeamMember teamMember1 = new TeamMember(1l, "a", "a", "a", 40d
                , "a", Role.ADMIN, Status.ACTIVE);
        teamMemberRepository.save(teamMember1);

        Project project = new Project(1L, "a", "a", client1, teamMember1);
        projectRepository.save(project);


        Category category = new Category(1L, "y");

        categoryRepository.save(category);

        Work work = new Work(1L, project, category, "a",5D,5D, new Date());
        workRepository.save(work);
    }
    @Test
    public void findAll() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/works")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void findById() throws Exception {

        mockMvc.perform(get("http://localhost:8080/api/works/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findForDay() throws Exception {

        mockMvc.perform(get("http://localhost:8080/api/works/for-day?year=2019&month=4&day=10")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void search() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/works/search?")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void add() throws Exception {
        mockMvc.perform(post("http://localhost:8080/api/works")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":2,\"name\":\"c\",\"description\":\"c\",\"date\" :\"2015-05-05\",\"time\" :40,\"" +
                        "overtime\": 20,\"project\":1,\"category\":1}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void edit() throws Exception {

        mockMvc.perform(put("http://localhost:8080/api/works")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":2,\"name\":\"cEdit\",\"description\":\"c\",\"date\" :\"2015-05-05\",\"time\" :40," +
                        "\"overtime\": 20,\"project\":1,\"category\":1}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    //TODO pitaj
    //@Test
    public void remove() throws Exception {
        mockMvc.perform(delete("http://localhost:8080/api/works/2"))
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
    public void setTeamMemberRepository(TeamMemberRepository teamMemberRepository) {
        this.teamMemberRepository = teamMemberRepository;
    }

    @Autowired
    public void setProjectRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setWorkRepository(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }
}
