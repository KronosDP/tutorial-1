package id.ac.ui.cs.advprog.eshop.model;

import enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private List<Product> products;
    private List<Order> orders;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("123-85460-84690");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);

        Product product2 = new Product();
        product2.setProductId("7532945748-84379608253-843968943");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);

        this.products.add(product1);
        this.products.add(product2);

        Order order1 = new Order("35896346983405680-493280698594-8439366089428", this.products, 1708560000L,
                "Safira Sudrajat");
        Order order2 = new Order("358321908394258843-84936083598-4938068928549", this.products, 1708570000L,
                "Bambang Bambang");

        this.orders.add(order1);
        this.orders.add(order2);
    }

    @Test
    void testCreatePaymentVoucherCodeSuccess() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("385390-895640-hdiwgoir", this.orders.getFirst(), "VOUCHER_CODE",
                paymentData);

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherCharacterInvalid() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678iferjqgkjoerjgkoje");
        Payment payment = new Payment("385390-895640-hdiwgoir", this.orders.getFirst(), "VOUCHER_CODE",
                paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherNotStartingEshop() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "blablablaESHOP1234ABC567");
        Payment payment = new Payment("385390-895640-hdiwgoir", this.orders.getFirst(), "VOUCHER_CODE",
                paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherRejectVoucherCodeNo8Numerals() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC567D85802938590");
        Payment payment = new Payment("385390-895640-hdiwgoir", this.orders.getFirst(), "VOUCHER_CODE",
                paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusPaymentVoucherSucess() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC567D");
        Payment payment = new Payment("385390-895640-hdiwgoir", this.orders.getFirst(), "VOUCHER_CODE",
                paymentData);

        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getOrder().getStatus());
    }

    @Test
    void testSetStatusPaymentVoucherRejected() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC567D");
        Payment payment = new Payment("385390-895640-hdiwgoir", this.orders.getFirst(), "VOUCHER_CODE",
                paymentData);

        payment.setStatus("REJECTED");
        assertEquals("FAILED", payment.getOrder().getStatus());
    }

    @Test
    void testCreateOrderInvalidStatus() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC567D");
        Payment payment = new Payment("385390-895640-hdiwgoir", this.orders.getFirst(), "VOUCHER_CODE",
                paymentData);

        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });
    }
}