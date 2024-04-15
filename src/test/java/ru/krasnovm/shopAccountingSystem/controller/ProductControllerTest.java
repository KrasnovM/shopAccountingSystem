package ru.krasnovm.shopAccountingSystem.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.krasnovm.shopAccountingSystem.dto.ProductDTO;
import ru.krasnovm.shopAccountingSystem.entity.Category;
import ru.krasnovm.shopAccountingSystem.entity.Product;
import ru.krasnovm.shopAccountingSystem.service.ProductService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)   //or @WithMockUser(username = "john", roles = { "VIEWER" }) under @Test
                                            //and @Test(expected = AccessDeniedException.class) @WithAnonymousUser
                                            //https://www.baeldung.com/spring-security-method-security
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private List<Product> products;
    private List<ProductDTO> productDTOS;

    @BeforeEach
    void setUp() {
        products = List.of(
                new Product(1L, "Apple", 20.0d, 205L, new Category(1L, "Fruit")),
                new Product(2L, "NescafeGold", 85.3d, 123L, new Category(2L, "Coffee"))
        );
        productDTOS = List.of(
                new ProductDTO("Apple", 205L, 20.0d, 1L),
                new ProductDTO("NescafeGold", 123L, 85.3d, 2L)
        );
    }

    @Test
    void create() {
    }

    @Test //readAll
    void shouldGetAllProducts() throws Exception {
        String response = """
                [
                    {
                        "id":1,
                        "name":"Apple",
                        "price":20.0,
                        "amount":205,
                        "category":
                            {
                                "id":1,
                                "name":"Fruit"
                            }
                        },
                        {
                            "id":2,
                            "name":"NescafeGold",
                            "price":85.3,
                            "amount":123,
                            "category":
                                {
                                    "id":2,
                                    "name":"Coffee"
                                }
                            }
                        ]
                """;
        when(productService.readAll()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().json(response, false));
    }

    @Test //readByCategoryId
    void shouldFindCategoryId() throws Exception {
        Product product = products.get(0);
        String response = STR."""
        [
            {
                "id":\{product.getId()},
                "name":\{product.getName()},
                "price":\{product.getPrice()},
                "amount":\{product.getAmount()},
                "category":
                    {
                        "id":\{product.getCategory().getId()},
                        "name":\{product.getCategory().getName()}
                    }
                }
        ]
        """;

        when(productService.readByCategoryId(1L))
                .thenReturn(List.of(products.get(0)));

        mockMvc.perform(get("/products/category/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }
}