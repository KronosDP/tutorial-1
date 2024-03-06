package id.ac.ui.cs.advprog.eshop.model;

import enums.OrderStatus;
import enums.PaymentWays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private List<Product> products = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

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
        Payment payment = new PaymentVoucherCode("385390-895640-hdiwgoir", this.orders.getFirst(),
                PaymentWays.VOUCHER_CODE
                        .getValue(),
                paymentData);

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherCharacterInvalid() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678iferjqgkjoerjgkoje");
        Payment payment = new PaymentVoucherCode("385390-895640-hdiwgoir", this.orders.getFirst(),
                PaymentWays.VOUCHER_CODE.getValue(),
                paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherNotStartingEshop() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "blablablaESHOP1234ABC567");
        Payment payment = new PaymentVoucherCode("385390-895640-hdiwgoir", this.orders.getFirst(),
                PaymentWays.VOUCHER_CODE.getValue(),
                paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherRejectVoucherCodeNo8Numerals() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC567D85802938590");
        Payment payment = new PaymentVoucherCode("385390-895640-hdiwgoir", this.orders.getFirst(),
                PaymentWays.VOUCHER_CODE.getValue(),
                paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusPaymentVoucherSucess() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC567D");
        Payment payment = new PaymentVoucherCode("385390-895640-hdiwgoir", this.orders.getFirst(),
                PaymentWays.VOUCHER_CODE.getValue(),
                paymentData);

        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getOrder().getStatus());
    }

    @Test
    void testSetStatusPaymentVoucherRejected() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC567D");
        Payment payment = new PaymentVoucherCode("385390-895640-hdiwgoir", this.orders.getFirst(),
                PaymentWays.VOUCHER_CODE.getValue(),
                paymentData);

        payment.setStatus("REJECTED");
        assertEquals("FAILED", payment.getOrder().getStatus());
    }

    @Test
    void testCreateOrderInvalidStatus() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC567D");
        Payment payment = new PaymentVoucherCode("385390-895640-hdiwgoir", this.orders.getFirst(),
                PaymentWays.VOUCHER_CODE.getValue(),
                paymentData);

        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });
    }

    @Test
    void testCreatePaymentCODSuccess() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("address", "Jl. Pensil Raya No. 8, Jakarta Selatan");
        paymentData.put("deliveryFee", "7000");
        Payment payment = new PaymentCOD("24893048-834950854-869548608", this.orders.getFirst(),
                PaymentWays.CASH_ON_DELIVERY.getValue(), paymentData);

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentCODRejectedNoAddress() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("deliveryFee", "12000");
        Payment payment = new PaymentCOD("24893048-834950854-869548608", this.orders.getFirst(),
                PaymentWays.CASH_ON_DELIVERY.getValue(), paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentCODRejectedNoDeliveryFee() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("address", "Jalan Anggur");
        Payment payment = new PaymentCOD("24893048-834950854-869548608", this.orders.getFirst(),
                PaymentWays.CASH_ON_DELIVERY.getValue(), paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusPaymentCODSucess() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("address", "Jalan Anggur");
        paymentData.put("deliveryFee", "12000");
        Payment payment = new PaymentCOD("24893048-834950854-869548608", this.orders.getFirst(),
                PaymentWays.CASH_ON_DELIVERY.getValue(), paymentData);

        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getOrder().getStatus());
    }

    @Test
    void testSetStatusPaymentCODRejected() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("address", "Jalan Anggur");
        paymentData.put("deliveryFee", "12000");
        Payment payment = new PaymentCOD("24893048-834950854-869548608", this.orders.getFirst(),
                PaymentWays.CASH_ON_DELIVERY.getValue(), paymentData);

        payment.setStatus("REJECTED");
        assertEquals("FAILED", payment.getOrder().getStatus());
    }

    @Test
    void testCreateOrderInvalidMethod() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC567D");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new PaymentVoucherCode("24893048-834950854-869548608", this.orders.getFirst(), "MEOW",
                    paymentData);
        });
    }
}