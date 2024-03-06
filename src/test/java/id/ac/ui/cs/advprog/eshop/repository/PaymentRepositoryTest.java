package id.ac.ui.cs.advprog.eshop.repository;

import enums.PaymentWays;
import id.ac.ui.cs.advprog.eshop.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments = new ArrayList<>();

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("4359801589-65249068950-48329508324");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        List<Order> orders = new ArrayList<>();
        Order order1 = new Order("582390589-8564908698-5460", products, 1708560000L, "Safira Sudrajat");
        Order order2 = new Order("45320859324-89436802598-58406", products, 1708570000L, "Safira Sudrajat");
        Order order3 = new Order("58903859248-586932684086-854960", products, 1708570000L, "Bambang Sudrajat");
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);

        Map<String, String> paymentData1 = new HashMap<String, String>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new PaymentVoucherCode("42869048-758693576985-test1", orders.getFirst(),
                PaymentWays.VOUCHER_CODE
                        .getValue(),
                paymentData1);

        Map<String, String> paymentData2 = new HashMap<String, String>();
        paymentData2.put("address", "Jl Kebon Jeruk No 5");
        paymentData2.put("deliveryFee", "12000");
        Payment payment2 = new PaymentCOD("42869048-758693576985-test2", orders.getFirst(),
                PaymentWays.CASH_ON_DELIVERY.getValue(),
                paymentData2);

        payments.add(payment1);
        payments.add(payment2);
    }

    @Test
    void testSaveCreate() {
        Payment payment = payments.get(0);
        Payment result = paymentRepository.save(payment);

        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getMethod(), result.getMethod());
        assertEquals(payment.getStatus(), result.getStatus());
        assertEquals(payment.getPaymentData(), result.getPaymentData());
        assertSame(payment.getOrder(), result.getOrder());
    }

    @Test
    void testSaveUpdate() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment payment = payments.get(1);
        payment.setStatus("SUCCESS");
        Payment result = paymentRepository.save(payment);

        String paymentId = payments.get(1).getId();
        Payment findResult = paymentRepository.findById(paymentId);

        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getStatus(), result.getStatus());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
        assertSame(payment.getOrder(), findResult.getOrder());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById(payments.get(0).getId());
        assertEquals(payments.get(0).getId(), findResult.getId());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById("zczc");
        assertNull(findResult);
    }

    @Test
    void testFindAll() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        List<Payment> paymentList = paymentRepository.getAllPayment();
        assertEquals(2, paymentList.size());
    }

}