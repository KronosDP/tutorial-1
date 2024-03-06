package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class PaymentVoucherCode extends Payment {
    public PaymentVoucherCode(String id, Order order, String method, Map<String, String> paymentData) {
        super(id, order, method, paymentData);
    }

    @Override
    public boolean validatePaymentMethod(String method, Map<String, String> paymentData) {
        String voucherCode = paymentData.get("voucherCode");
        if (voucherCode != null && voucherCode.length() == 16 && voucherCode.startsWith("ESHOP")) {
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
}
