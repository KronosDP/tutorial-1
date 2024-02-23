package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class APIController {

        @Autowired
        private ProductService productService;

        @Autowired
        private CarService carService;

        @GetMapping("/products")
        public List<Product> getAllProducts() {
                return productService.findAll();
        }

        @GetMapping("/cars")
        public List<Car> getAllCars() {
                return carService.findAll();
        }

}
