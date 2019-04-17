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
public class WorkTest {
    private MockMvc mockMvc;

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(
                        get("http://localhost:8080/api/works")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findById() throws Exception {

        mockMvc.perform(
                        get("http://localhost:8080/api/works/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findForDay() throws Exception {

        mockMvc.perform(
                        get("http://localhost:8080/api/works/for-day?year=2019&month=4&day=10")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void search() throws Exception {
        mockMvc.perform(
                        get("http://localhost:8080/api/works/search?")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void add() throws Exception {
        mockMvc.perform(
                        post("http://localhost:8080/api/works")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        "{\"id\":3,\"name\":\"c\",\"description\":\"c\",\"date\" :\"2015-05-05\",\"time\" :40,\""
                                                + "overtime\": 20,\"project\":1,\"category\":1,\"employee\":1}")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void edit() throws Exception {

        mockMvc.perform(
                        put("http://localhost:8080/api/works")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        "{\"id\":3,\"name\":\"cEdit\",\"description\":\"c\",\"date\" :\"2015-05-05\",\"time\" :40,"
                                                + "\"overtime\": 20,\"project\":1,\"category\":1,\"employee\":1}")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void remove() throws Exception {
        mockMvc.perform(delete("http://localhost:8080/api/works/2")).andExpect(status().isOk());
    }

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }
}
