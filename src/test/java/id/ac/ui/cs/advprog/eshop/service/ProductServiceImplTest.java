package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        when(productRepository.create(product)).thenReturn(product);

        Product result = productService.create(product);

        assertEquals(product, result);
        verify(productRepository, times(1)).create(product);
    }

    @Test
    public void testFindAllProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2).iterator());

        List<Product> result = productService.findAll();

        assertEquals(2, result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testFindProductById() {
        Product product = new Product();
        when(productRepository.findById("1")).thenReturn(product);

        Product result = productService.findById("1");

        assertEquals(product, result);
        verify(productRepository, times(1)).findById("1");
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product();
        when(productRepository.update("1", product)).thenReturn(product);

        Product result = productService.update("1", product);

        assertEquals(product, result);
        verify(productRepository, times(1)).update("1", product);
    }

    @Test
    public void testDeleteProduct() {
        productService.delete("1");

        verify(productRepository, times(1)).delete("1");
    }
}
