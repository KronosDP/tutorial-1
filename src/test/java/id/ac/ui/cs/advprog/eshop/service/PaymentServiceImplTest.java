package id.ac.ui.cs.advprog.eshop.service;

import enums.PaymentStatus;
import enums.PaymentWays;
import id.ac.ui.cs.advprog.eshop.model.*;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;
    List<Map<String, String>> paymentDataList;
    List<Order> orders;
    List<Product> products;

    @BeforeEach
    void setUp() {

        // Make product
        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("4359801589-65249068950-48329508324");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        // Make order
        orders = new ArrayList<>();
        Order order1 = new Order("582390589-8564908698-5460", products, 1708560000L, "Safira Sudrajat");
        Order order2 = new Order("45320859324-89436802598-58406", products, 1708570000L, "Safira Sudrajat");
        Order order3 = new Order("58903859248-586932684086-854960", products, 1708570000L, "Bambang Sudrajat");
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);

        // Make payment datas
        paymentDataList = new ArrayList<>();
        Map<String, String> paymentData1 = new HashMap<String, String>();
        Map<String, String> paymentData2 = new HashMap<String, String>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");
        paymentData2.put("address", "Jl Kebon Jeruk No 5");
        paymentData2.put("deliveryFee", "12000");

        paymentDataList.add(paymentData1);
        paymentDataList.add(paymentData2);
    }

    @Test
    void testCreatePaymentVoucherCode() {
        String id = "58903859248-586932684086-854960-ekgoejrigjoew";
        Payment payment = new PaymentVoucherCode(id, orders.get(1), PaymentWays.VOUCHER_CODE.getValue(),
                paymentDataList.get(0));

        doReturn(null).when(paymentRepository).findById(id);
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(id, orders.get(1), PaymentWays.VOUCHER_CODE.getValue(),
                paymentDataList.get(0));

        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testCreatePaymentCOD() {
        String id = "58903859248-586932684086-854960-ekgoejrigjoew";
        Payment payment = new PaymentCOD(id, orders.get(1), PaymentWays.CASH_ON_DELIVERY.getValue(),
                paymentDataList.get(0));

        doReturn(null).when(paymentRepository).findById(id);
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(id, orders.get(1), PaymentWays.CASH_ON_DELIVERY.getValue(),
                paymentDataList.get(0));

        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testCreatePaymentIfAlreadyExists() {
        String id = "75c64e96-d4d7-454b-8ee5-7086efff516c";
        Payment payment = new PaymentVoucherCode(id, orders.get(1), PaymentWays.VOUCHER_CODE.getValue(),
                paymentDataList.get(0));
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        assertNull(paymentService.addPayment(id, orders.get(1), PaymentWays.VOUCHER_CODE.getValue(),
                paymentDataList.get(0)));
        verify(paymentRepository, times(0)).save(payment);
    }

    @Test
    void testSetStatus() {
        Payment payment = new PaymentCOD("75c64e96-d4d7-454b-8ee5-7086efff516c", orders.get(1),
                PaymentWays.CASH_ON_DELIVERY.getValue(),
                paymentDataList.get(0));
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());

        Payment newPayment = new PaymentVoucherCode("75c64e96-d4d7-454b-8ee5-7086efff516c", orders.get(1),
                PaymentWays.VOUCHER_CODE.getValue(),
                paymentDataList.get(0));

        doReturn(payment).when(paymentRepository).findById(payment.getId());
        doReturn(newPayment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());

        assertEquals(payment.getId(), result.getId());
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetStatusInvalidStatus() {
        Payment payment = new PaymentCOD("75c64e96-d4d7-454b-8ee5-7086efff516c", orders.get(1),
                PaymentWays.CASH_ON_DELIVERY.getValue(),
                paymentDataList.get(0));
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());

        doReturn(payment).when(paymentRepository).findById(payment.getId());

        assertThrows(IllegalArgumentException.class,
                () -> paymentService.setStatus(payment, "MEOW"));

        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testSetStatusInvalidPaymentId() {
        Payment payment = new PaymentCOD("75c64e96-d4d7-454b-8ee5-7086efff516c", orders.get(1),
                PaymentWays.CASH_ON_DELIVERY.getValue(),
                paymentDataList.get(0));

        doReturn(null).when(paymentRepository).findById(payment.getId());

        assertThrows(NoSuchElementException.class,
                () -> paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue()));

        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testGetPaymentIfIdFound() {
        Payment payment = new PaymentCOD("75c64e96-d4d7-454b-8ee5-7086efff516c", orders.get(1),
                PaymentWays.CASH_ON_DELIVERY.getValue(),
                paymentDataList.get(0));

        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testGetPaymentIfIfIdNotFound() {
        Payment payment = new PaymentCOD("75c64e96-d4d7-454b-8ee5-7086efff516c", orders.get(1),
                PaymentWays.CASH_ON_DELIVERY.getValue(),
                paymentDataList.get(0));

        assertNull(paymentService.getPayment(payment.getId()));
    }

    @Test
    void testGetAllPayments() {
        Payment payment = new PaymentCOD("75c64e96-d4d7-454b-8ee5-7086efff516c", orders.get(1),
                PaymentWays.CASH_ON_DELIVERY.getValue(),
                paymentDataList.get(0));
        List<Payment> payments = new ArrayList<>();
        payments.add(payment);

        doReturn(payments).when(paymentRepository).getAllPayment();

        List<Payment> results = paymentService.getAllPayments();
        assertEquals(payments, results);

        assertEquals(1, results.size());
    }

    @Test
    void testGetAllPaymentsIfEmpty() {
        List<Payment> payments = new ArrayList<>();

        doReturn(payments).when(paymentRepository).getAllPayment();

        List<Payment> results = paymentService.getAllPayments();
        assertEquals(payments, results);

        assertEquals(0, results.size());
    }
}
