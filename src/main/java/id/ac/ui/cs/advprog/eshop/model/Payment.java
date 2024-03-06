package id.ac.ui.cs.advprog.eshop.model;

import enums.OrderStatus;
import enums.PaymentStatus;
import enums.PaymentWays;
import lombok.Getter;

import java.util.Map;

@Getter
public abstract class Payment {
    protected String id;
    protected String method;
    protected String status;
    protected Map<String, String> paymentData;
    protected Order order;

    public Payment(String id, Order order, String method, Map<String, String> paymentData) {
        this.id = id;
        this.order = order;
        validateAndSetPaymentMethod(method);
        this.paymentData = paymentData;
        setStatus(validatePaymentMethod(method, paymentData) ? PaymentStatus.SUCCESS.getValue()
                : PaymentStatus.REJECTED.getValue());
    }

    public Payment(String id, Order order, String method, Map<String, String> paymentData, String status) {
        this.id = id;
        this.order = order;
        validateAndSetPaymentMethod(method);
        this.paymentData = paymentData;
        setStatus(status);
    }

    private void validateAndSetPaymentMethod(String method) {
        if (!isValidPaymentMethod(method)) {
            throw new IllegalArgumentException("Invalid payment method");
        }
        this.method = method;
    }

    private boolean isValidPaymentMethod(String method) {
        return method.equals(PaymentWays.CASH_ON_DELIVERY.getValue())
                || method.equals(PaymentWays.VOUCHER_CODE.getValue());
    }

    public abstract boolean validatePaymentMethod(String method, Map<String, String> paymentData);

    public void setStatus(String status) {
        switch (PaymentStatus.valueOf(status)) {
            case SUCCESS:
                this.status = status;
                order.setStatus(OrderStatus.SUCCESS.getValue());
                break;
            case REJECTED:
                this.status = status;
                order.setStatus(OrderStatus.FAILED.getValue());
                break;
            default:
                throw new IllegalArgumentException("Invalid status");
        }
    }

}
