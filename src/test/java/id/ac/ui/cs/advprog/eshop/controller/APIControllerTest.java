package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class APIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    @Test
    public void testListProduct() throws Exception {
        Product product1 = new Product();
        product1.setProductId(UUID.randomUUID().toString());
        product1.setProductName("Product 1");
        product1.setProductQuantity(10);

        Product product2 = new Product();
        product2.setProductId(UUID.randomUUID().toString());
        product2.setProductName("Product 2");
        product2.setProductQuantity(20);

        List<Product> products = Arrays.asList(product1, product2);

        when(service.findAll()).thenReturn(products);

        mockMvc.perform(MockMvcRequestBuilders.get("/api"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'productId': '" + product1.getProductId()
                        + "', 'productName': 'Product 1', 'productQuantity': 10},{'productId': '"
                        + product2.getProductId() + "', 'productName': 'Product 2', 'productQuantity': 20}]"));
    }
}
