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
    @Autowired
    private MockMvc mockMvc;


    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    TeamMemberRepository teamMemberRepository;
    @Autowired
    ProjectRepository projectRepository;

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

    }
    @Test
    public void findAll() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void findById() throws Exception {

        mockMvc.perform(get("http://localhost:8080/api/projects/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void add() throws Exception {
        mockMvc.perform(post("http://localhost:8080/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":2,\"name\":\"c\",\"description\":\"c\",\"client\":{\"id\":1},\"lead\":{\"id\":1}}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void edit() throws Exception {

        mockMvc.perform(put("http://localhost:8080/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":2,\"name\":\"editC\",\"description\":\"c\",\"client\":{\"id\":1},\"lead\":{\"id\":1}}")

                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
    //TODO pitaj
    //@Test
    public void remove() throws Exception {
        mockMvc.perform(delete("http://localhost:8080/api/projects/2"))
                .andExpect(status().isOk());
    }



}
