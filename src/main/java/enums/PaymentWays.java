package enums;

import lombok.Getter;

@Getter
public enum PaymentWays {
    CASH_ON_DELIVERY("CASH_ON_DELIVERY"),
    VOUCHER_CODE("VOUCHER_CODE");

    private final String value;

    private PaymentWays(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        for (PaymentWays paymentWays : PaymentWays.values()) {
            if (paymentWays.name().equals(param)) {
                return true;
            }
        }
        return false;
    }
}
