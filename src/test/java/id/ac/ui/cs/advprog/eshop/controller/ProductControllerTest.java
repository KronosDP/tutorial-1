package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.controller.ProductController;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ProductControllerTest {

    @InjectMocks
    ProductController productController;

    @Mock
    ProductService service;

    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEditProductPage() {
        Product product = new Product();
        product.setProductId("532469849684368932829038-84932680324869034869023-8439026893248608");
        product.setProductQuantity(10);
        product.setProductName("Product Name");
        when(service.findById(anyString())).thenReturn(product);

        String viewName = productController
                .editProductPage("532469849684368932829038-84932680324869034869023-8439026893248608",
                        model);

        verify(service, times(1)).findById(anyString());
        verify(model, times(1)).addAttribute("product", product);
        assertEquals("editProduct", viewName);
    }

    @Test
    void testEditProductPost() {
        Product product = new Product();
        product.setProductId("532469849684368932829038-84932680324869034869023-8439026893248608");
        product.setProductQuantity(10);
        product.setProductName("Product Name");

        String viewName = productController
                .editProductPost("532469849684368932829038-84932680324869034869023-8439026893248608",
                        product, model);

        verify(service, times(1)).update(anyString(), any(Product.class));
        assertEquals("redirect:../list", viewName);
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        product.setProductId("532469849684368932829038-84932680324869034869023-8439026893248608");
        product.setProductQuantity(10);
        product.setProductName("Product Name");
        when(service.create(any(Product.class))).thenReturn(product);

        String viewName = productController.createProductPost(product, model);

        verify(service, times(1)).create(any(Product.class));
        assertEquals("redirect:list", viewName);
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);

        verify(model, times(1)).addAttribute(eq("product"), any(Product.class));
        assertEquals("createProduct", viewName);
    }

    @Test
    void testProductListPage() {
        List<Product> allProducts = Arrays.asList(new Product(), new Product());
        when(service.findAll()).thenReturn(allProducts);

        String viewName = productController.productListPage(model);

        verify(service, times(1)).findAll();
        verify(model, times(1)).addAttribute("products", allProducts);
        assertEquals("listProduct", viewName);
    }

    @Test
    void testDeleteProduct() {
        String id = "532469849684368932829038-84932680324869034869023-8439026893248608";
        doNothing().when(service).delete(anyString());

        String viewName = productController.deleteProduct(id, model);

        verify(service, times(1)).delete(anyString());
        assertEquals("redirect:../list", viewName);
    }

}
