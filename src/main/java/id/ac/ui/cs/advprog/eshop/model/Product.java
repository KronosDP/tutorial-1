package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private static int idCounter = 0;
    private String productId;
    private String productName;
    private int productQuantity;

    public Product() {
        this.productId = "P" + (idCounter++);
    }
}