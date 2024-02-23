package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String productId;
    private String productName;
    private int productQuantity;

    public void setProductQuantity(int productQuantity) {
        if (productQuantity < 0) {
            throw new IllegalArgumentException("Product quantity cannot be negative");
        }
        this.productQuantity = productQuantity;
    }

    public void setProductId(String productId) {
        if (productId == null || productId.isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be empty");
        }
        this.productId = productId;
    }
}