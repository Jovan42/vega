package vega.it.praksa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TeamTest {
    private MockMvc mockMvc;

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(
                get("http://localhost:8080/api/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllMembers() throws Exception {
        mockMvc.perform(
                get("http://localhost:8080/api/teams/1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void addEmployeeAndRemove() throws Exception {
        mockMvc.perform(
                post("http://localhost:8080/api/teams/1/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(
                delete("http://localhost:8080/api/teams/1/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findById() throws Exception {

        mockMvc.perform(
                get("http://localhost:8080/api/teams/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void add() throws Exception {
        mockMvc.perform(
                post("http://localhost:8080/api/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                "{\"id\":3,\"name\":\"Team 3\",\"teamLeader\":\"1\",\"employees\":[]}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void edit() throws Exception {
        mockMvc.perform(
                put("http://localhost:8080/api/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                "{\"id\":3,\"name\":\"Team 3\",\"teamLeader\":\"1\",\"employees\":[]}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void remove() throws Exception {
        mockMvc.perform(delete("http://localhost:8080/api/teams/2"))
                .andExpect(status().isOk());
    }

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }
}
