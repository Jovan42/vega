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
import vega.it.praksa.model.Category;
import vega.it.praksa.model.Client;
import vega.it.praksa.model.Country;
import vega.it.praksa.repositories.CategoryRepository;
import vega.it.praksa.repositories.ClientRepository;
import vega.it.praksa.repositories.CountryRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClientTest {
    private MockMvc mockMvc;
    private ClientRepository clientRepository;
    private CountryRepository countryRepository;

    @Before
    public void setUp() {
        Country country = new Country(1L, "a");
        countryRepository.save(country);

        Client client1 = new Client(1L, "a", "a", "a", "a", country);
        Client client2 = new Client(2L, "b", "b", "b", "b", country);


        clientRepository.save(client1);
        clientRepository.save(client2);

    }
    @Test
    public void findAll() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void findById() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/clients/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void add() throws Exception {
        mockMvc.perform(post("http://localhost:8080/api/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":3,\"name\":\"c\",\"address\":\"c\",\"city\":\"c\",\"zipCode\":\"c\"," +
                        "\"country\":{\"id\":1}}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void edit() throws Exception {
        mockMvc.perform(put("http://localhost:8080/api/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":3,\"name\":\"editC\",\"address\":\"c\",\"city\":\"c\",\"zipCode\":\"c\"," +
                        "\"country\":{\"id\":1}}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
    //TODO pitaj
    //@Test
    public void remove() throws Exception {
        mockMvc.perform(delete("http://localhost:8080/api/clients/2"))
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
}
