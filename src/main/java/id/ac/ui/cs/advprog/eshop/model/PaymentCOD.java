package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class PaymentCOD extends Payment {
    public PaymentCOD(String id, Order order, String method, Map<String, String> paymentData) {
        super(id, order, method, paymentData);
    }

    @Override
    public boolean validatePaymentMethod(String method, Map<String, String> paymentData) {
        String address = paymentData.get("address");
        String deliveryFee = paymentData.get("deliveryFee");
        return address != null && !address.isEmpty() && deliveryFee != null && !deliveryFee.isEmpty();
    }
}
