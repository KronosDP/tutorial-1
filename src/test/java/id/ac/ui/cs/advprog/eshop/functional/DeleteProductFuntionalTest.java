package id.ac.ui.cs.advprog.eshop.functional;

import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)

public class DeleteProductFuntionalTest {

        @LocalServerPort
        private int serverPort;

        @Value("${app.baseUrl:http://localhost}")
        private String testbaseUrl;
        private String baseUrl;

        @BeforeEach
        void setUp() {
                baseUrl = String.format("%s:%d%s", testbaseUrl, serverPort, "/product/create");
                ProductRepository productRepository = new ProductRepository();
                productRepository.deleteAll();
        }

        @AfterEach
        void tearDown() {
                ProductRepository productRepository = new ProductRepository();
                productRepository.deleteAll();
        }

        @Test
        void createAndDelete(ChromeDriver driver) {
                driver.get(baseUrl);

                WebElement name = driver.findElement(By.name("productName"));
                name.sendKeys("Product 1");

                WebElement quantity = driver.findElement(By.name("productQuantity"));
                quantity.sendKeys("100");

                WebElement submit = driver.findElement(By.id("submitButton"));
                submit.click();

                // On the table, look for the first row and fourth columnn (go to delete)
                WebElement deleteButton = driver.findElement(By.xpath("//table/tbody/tr[1]/td[4]/a"));
                deleteButton.click();

                // Check if the product is deleted
                List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
                assertEquals(101, rows.size());
        }

}
