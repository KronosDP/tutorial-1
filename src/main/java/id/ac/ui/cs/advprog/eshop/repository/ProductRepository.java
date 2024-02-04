package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String id) {
        for (Product product : productData) {
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public Product update(String id, Product product) {
        for (int i = 0; i < productData.size(); i++) {
            Product currentProduct = productData.get(i);
            if (currentProduct.getProductId().equals(id)) {
                currentProduct.setProductName(product.getProductName());
                currentProduct.setProductQuantity(product.getProductQuantity());
                return product;
            }
        }
        return null;
    }

    public void delete(String id) {
        for (int i = 0; i < productData.size(); i++) {
            Product currentProduct = productData.get(i);
            if (currentProduct.getProductId().equals(id)) {
                productData.remove(i);
                return;
            }
        }
    }
}
