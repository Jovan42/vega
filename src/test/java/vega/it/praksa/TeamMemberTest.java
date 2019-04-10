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
import vega.it.praksa.model.TeamMember;
import vega.it.praksa.model.enums.Role;
import vega.it.praksa.model.enums.Status;
import vega.it.praksa.repositories.TeamMemberRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TeamMemberTest {
    private MockMvc mockMvc;
    private TeamMemberRepository teamMemberRepository;

    @Before
    public void setUp() {

        TeamMember teamMember1 = new TeamMember(1l, "a", "a", "a", 40d
                , "a", Role.ADMIN, Status.ACTIVE);
        TeamMember teamMember2 = new TeamMember(2l, "b", "b", "b", 40d
                , "b", Role.WORKER, Status.INACTIVE);

        teamMemberRepository.save(teamMember1);
        teamMemberRepository.save(teamMember2);

    }
    @Test
    public void findAll() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/team-members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void findById() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/team-members/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void add() throws Exception {
        mockMvc.perform(post("http://localhost:8080/api/team-members")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":3,\"username\":\"c\",\"password\":" +
                        "\"\",\"name\":\"a\",\"hoursPerWeek\":40.0,\"email\":\"a\",\"role\":\"ADMIN\",\"status\":\"" +
                        "ACTIVE\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void edit() throws Exception {
        mockMvc.perform(put("http://localhost:8080/api/team-members")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"username\":\"edit\",\"password\":" +
                        "\"\",\"name\":\"a\",\"hoursPerWeek\":40.0,\"email\":\"a\",\"role\":\"ADMIN\",\"status\":\"" +
                        "ACTIVE\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
    //TODO pitaj
    //@Test
    public void remove() throws Exception {
        mockMvc.perform(delete("http://localhost:8080/api/team-members/2"))
                .andExpect(status().isOk());
    }

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Autowired
    public void setTeamMemberRepository(TeamMemberRepository teamMemberRepository) {
        this.teamMemberRepository = teamMemberRepository;
    }
}
