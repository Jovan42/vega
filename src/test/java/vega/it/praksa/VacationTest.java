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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VacationTest {
    private MockMvc mockMvc;

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(
                        get("http://localhost:8080/api/vacations")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findById() throws Exception {

        mockMvc.perform(
                        get("http://localhost:8080/api/vacations/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void add() throws Exception {
        mockMvc.perform(
                        post("http://localhost:8080/api/vacations")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        "{\"id\":3,\"startDate\":\"2019-04-17\",\"endDate\":\"2019-04-17\",\"employee\":2}")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void edit() throws Exception {
        mockMvc.perform(
                        put("http://localhost:8080/api/vacations")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        "{\"id\":3,\"startDate\":\"2019-04-17\",\"endDate\":\"2019-04-17\",\"employee\":2}")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void remove() throws Exception {
        mockMvc.perform(delete("http://localhost:8080/api/vacations/2"))
                .andExpect(status().isOk());
    }

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }
}
