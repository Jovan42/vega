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
import vega.it.praksa.repositories.CategoryRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryTest {
    private MockMvc mockMvc;
    private CategoryRepository categoryRepository;

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Autowired
    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Before
    public void setUp() {
        Category category1 = new Category(1L, "a");
        Category category2 = new Category(2L, "b");
        Category category3 = new Category(3L, "x");
        Category category4 = new Category(4L, "y");

        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);
        categoryRepository.save(category4);
    }

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(
                        get("http://localhost:8080/api/categories")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findById() throws Exception {
        Category c = categoryRepository.findAll().get(0);

        mockMvc.perform(
                        get("http://localhost:8080/api/categories/" + c.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void add() throws Exception {
        mockMvc.perform(
                        post("http://localhost:8080/api/categories")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\":2,\"name\":\"c\"}")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void edit() throws Exception {
        Category c = categoryRepository.findAll().get(0);

        mockMvc.perform(
                        put("http://localhost:8080/api/categories")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\":" + c.getId() + ",\"name\":\"edit\"}")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    // @Test
    public void remove() throws Exception {
        Category c = categoryRepository.findAll().get(0);
        mockMvc.perform(delete("http://localhost:8080/api/categories/" + c.getId()))
                .andExpect(status().isOk());
    }
}
