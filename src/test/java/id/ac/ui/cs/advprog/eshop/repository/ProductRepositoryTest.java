package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

        @InjectMocks
        ProductRepository productRepository;

        @BeforeEach
        void setUp() {
        }

        // Create

        @Test
        void testCreateAndFind() {
                Product product = new Product();
                product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
                product.setProductName("Sampo Cap Bambang");
                product.setProductQuantity(100);
                productRepository.create(product);

                Iterator<Product> productIterator = productRepository.findAll();
                assertTrue(productIterator.hasNext());
                Product savedProduct = productIterator.next();
                assertEquals(savedProduct.getProductId(), product.getProductId());
                assertEquals(savedProduct.getProductName(), product.getProductName());
                assertEquals(savedProduct.getProductQuantity(), product.getProductQuantity());
        }

        @Test
        void testCreateWithNullProductId() {
                Product product = new Product();
                product.setProductName("Sampo Cap Bambang");
                product.setProductQuantity(1);
                assertThrows(IllegalArgumentException.class, () -> {
                        product.setProductId(null);
                        productRepository.create(product);
                });
        }

        @Test
        void testCreateWithQuantityLessThanZero() {
                Product product = new Product();
                product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
                product.setProductName("Sampo Cap Bambang");
                assertThrows(IllegalArgumentException.class, () -> {
                        product.setProductQuantity(-1);
                        productRepository.create(product);
                });
        }

        // Find

        @Test
        void testFindAllIfEmpty() {
                Iterator<Product> productIterator = productRepository.findAll();
                assertFalse(productIterator.hasNext());
        }

        @Test
        void testFindAllIfMoreThanOneProduct() {
                Product product1 = new Product();
                product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
                product1.setProductName("Sampo Cap Bambang");
                product1.setProductQuantity(100);
                productRepository.create(product1);

                Product product2 = new Product();
                product2.setProductId("a0f9de45-90b1-437d-a0bf-d0821dde9096");
                product2.setProductName("Sampo Cap Usep");
                product2.setProductQuantity(50);
                productRepository.create(product2);
        }

        @Test
        void testFindAllWithManyProducts() {
                for (int i = 0; i < 10_000; i++) {
                        Product product = new Product();
                        product.setProductName("Sampo Cap Bambang");
                        product.setProductQuantity(100);
                        productRepository.create(product);
                }

                Iterator<Product> productIterator = productRepository.findAll();
                for (int i = 0; i < 10_000; i++) {
                        assertTrue(productIterator.hasNext());
                        productIterator.next();
                }
                assertFalse(productIterator.hasNext());
        }

        @Test
        void testFindByIdWithManyProducts() {
                for (int i = 0; i < 10_000; i++) {
                        Product product = new Product();
                        product.setProductName("Sampo Cap Bambang");
                        product.setProductQuantity(100);
                        productRepository.create(product);
                }

                Iterator<Product> productIterator = productRepository.findAll();
                for (int i = 0; i < 10_000; i++) {
                        assertTrue(productIterator.hasNext());
                        Product product = productIterator.next();
                        assertNotNull(productRepository.findById(product.getProductId()));
                }
                assertFalse(productIterator.hasNext());
        }

        // Edit

        @Test
        void testEditProductName() {
                Product product = new Product();
                product.setProductName("Sampo Cap Bambang");
                product.setProductQuantity(1);
                productRepository.create(product);

                Product product2 = new Product();
                product2.setProductName("Sampo Cap Usep");
                product2.setProductQuantity(1);
                productRepository.update(product.getProductId(), product2);

                assertEquals(product2.getProductName(),
                                productRepository.findById(product.getProductId()).getProductName());
        }

        @Test
        void testEditProductQuantity() {
                Product product = new Product();
                product.setProductName("Sampo Cap Bambang");
                product.setProductQuantity(1);
                productRepository.create(product);

                Product product2 = new Product();
                product2.setProductName("Sampo Cap Bambang");
                product2.setProductQuantity(2);
                productRepository.update(product.getProductId(), product2);

                assertEquals(product2.getProductQuantity(),
                                productRepository.findById(product.getProductId()).getProductQuantity());
        }

        @Test
        void testEditWithQuantityLessThanZero() {
                Product product = new Product();
                product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
                product.setProductName("Sampo Cap Bambang");
                product.setProductQuantity(1);
                productRepository.create(product);

                Product product2 = new Product();
                product2.setProductName("Sampo Cap Bambang");
                assertThrows(IllegalArgumentException.class, () -> {
                        product2.setProductQuantity(-1);
                        productRepository.update(product.getProductId(), product2);
                });
        }

        // Delete

        @Test
        void testDeleteProduct() {
                Product product = new Product();
                product.setProductName("Sampo Cap Bambang");
                product.setProductQuantity(1);
                productRepository.create(product);

                productRepository.delete(product.getProductId());
                assertNull(productRepository.findById(product.getProductId()));
        }
}
