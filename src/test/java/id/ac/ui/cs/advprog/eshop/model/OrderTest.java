package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.bytebuddy.asm.Advice.OffsetMapping.Factory.Illegal;

public class OrderTest {
    private List<Product> products;

    @BeforeEach
    void setUp() {
        Product product1 = new Product();
        product1.setProductId("griwllgirgn-wgfjjkrgp-gwnojiort");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);

        Product product2 = new Product();
        product1.setProductId("djgfkejgi-sngjfhgjkwe-nkskvjgjrkso");
        product1.setProductName("Sampo Cap Usep");
        product1.setProductQuantity(1);

        this.products.add(product1);
        this.products.add(product2);
    }

    @Test
    void testCreateOrderEmptyProduct() {
        this.products.clear();

    assertThrows(IllegalArgumentException.class, () -> {
        Order order new Order("123456-6789", 
        this.products, 1708560000L, "Safira Sudrajat");
        });
    }

    @Test
    void TestCreateOrderDefaultStatus() {
        Order order = new Order("123456-6789",
                this.products, 1708560000L, "Safira Sudrajat");

        assertSame(this.products, order.getProducts());
        assertEquals(2, order.getProducts.size());
        assertEquals("Sampo Cap Bambang", order.getProducts().get(0).getProductName());
        assertEquals("Sampo Cap Usep", order.getProducts().get(1).getProductName());

        assertEquals("123456-6789", order.getId());
        assertEquals(1708560000L, order.getOrderTime());
        assertEquals("Safira Sudrajat", order.getAuthor());
        assertEquals("WAITING_PAYMENT", order.getStatus());
    }

    @Test
    void testCreateOrderSuccessStatus() {
        Order order = new Order("123456-6789",
                this.products, 1708560000L, "Safira Sudrajat", "SUCCESS");
        assertEquals("SUCCESS", order.getStatus());
    }

    @Test
    void testCreateOrderInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Order order = new Order("123456-6789",
                    this.products, 1708560000L, "Safira Sudrajat", "MEOW");
        });
    }

    @Test
    void testSetStatusToCancelled() {
        Order order = new Order("123456-6789",
                this.products, 1708560000L, "Safira Sudrajat");
        order.setStatus("CANCELLED");
        assertEquals("CANCELLED", order.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Order order = new Order("123456-6789",
                this.products, 1708560000L, "Safira Sudrajat");
        assertThrows(IllegalArgumentException.class, () -> {
            order.setStatus("MEOW");
        });
    }

}
