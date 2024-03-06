package id.ac.ui.cs.advprog.eshop.model;

import enums.OrderStatus;
import enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class Payment {

    String id;
    String method;
    String status;
    Map<String, String> paymentData;
    Order order;

    public Payment(String id, Order order, String method, Map<String, String> paymentData) {
        this.id = id;
        this.order = order;
        this.method = method;
        this.paymentData = paymentData;

        boolean valid = validatePaymentMethod(method, paymentData);

        if (valid) {
            setStatus(PaymentStatus.SUCCESS.getValue());
        } else {
            setStatus(PaymentStatus.REJECTED.getValue());
        }
    }

    public Payment(String id, Order order, String method, Map<String, String> paymentData, String status) {
        this.id = id;
        this.order = order;
        this.method = method;
        this.paymentData = paymentData;
        setStatus(status);
    }

    public void setStatus(String status) {
        switch (status) {
            case PaymentStatus.SUCCESS.getValue():
                this.status = status;
                order.setStatus(OrderStatus.SUCCESS.getValue());
                break;
            case PaymentStatus.REJECTED.getValue():
                this.status = status;
                order.setStatus(OrderStatus.FAILED.getValue());
                break;
            default:
                throw new IllegalArgumentException("Invalid status");
        }
    }

    private boolean validatePaymentMethod(String method, Map<String, String> paymentData) {
        boolean valid = false;
        switch (method) {
            case "VOUCHER_CODE":
                valid = validateVoucherCode(paymentData);
                break;
            case "CASH_ON_DELIVERY":
                valid = validateCOD(paymentData);
                break;
            default:
                throw new IllegalArgumentException("Invalid payment method");
        }
        return valid;
    }

    private boolean validateVoucherCode(Map<String, String> paymentData) {
        String voucherCode = paymentData.get("voucherCode");
        if (voucherCode != null
                && voucherCode.length() == 16
                && voucherCode.startsWith("ESHOP")) {
            int numCharCount = 0;
            for (char c : voucherCode.toCharArray()) {
                if (Character.isDigit(c)) {
                    numCharCount++;
                }
            }
            return numCharCount == 8;
        }
        return false;
    }

    private boolean validateCOD(Map<String, String> paymentData) {
        String address = paymentData.get("address");
        String deliveryFee = paymentData.get("deliveryFee");

        if (address != null && !address.isEmpty()
                && deliveryFee != null && !deliveryFee.isEmpty()) {
            return true;
        }
        return false;
    }

}