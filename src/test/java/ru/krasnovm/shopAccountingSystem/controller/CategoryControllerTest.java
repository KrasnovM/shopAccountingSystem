package ru.krasnovm.shopAccountingSystem.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.krasnovm.shopAccountingSystem.dto.CategoryDTO;
import ru.krasnovm.shopAccountingSystem.entity.Category;
import ru.krasnovm.shopAccountingSystem.service.CategoryService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    private List<Category> categories;
    private List<CategoryDTO> categoryDTOS;

    @BeforeEach
    void setUp() {
        categories = List.of(
                new Category(1L, "Toy"),
                new Category(2L, "Snack")
        );
        categoryDTOS = List.of(
                new CategoryDTO("Toy"),
                new CategoryDTO("Snack")
        );
    }

    @Test //create
    void createCategory() throws Exception {
        String response = """
                {
                    "id":1,
                    "name":"Toy"
                }
                """;

        String content = """
                {
                    "name":"Toy"
                }
                """;
        when(categoryService.create(categoryDTOS.get(0))).thenReturn(categories.get(0));

        mockMvc.perform(
                post("/categories")
                        .contentType("application/json")
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    @Test //readAll
    void shouldGetAllCategories() throws Exception {
        String response = """
                [
                    {
                        "id":1,
                        "name":"Toy"
                    },
                    {
                        "id":2,
                        "name":"Snack"
                    }
                ]
                """;

        when(categoryService.readAll()).thenReturn(categories);

        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    @Test //delete
    void shouldDeleteCategoryById() throws Exception{
        mockMvc.perform(delete("/categories/1"))
                .andExpect(status().isOk());
    }
}
