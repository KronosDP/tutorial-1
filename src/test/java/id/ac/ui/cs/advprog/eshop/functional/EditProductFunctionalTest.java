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
public class EditProductFunctionalTest {

        @LocalServerPort
        private int serverPort;

        @Value("${app.baseUrl:http://localhost}")
        private String testbaseUrl;
        private String baseUrl;

        @BeforeAll
        static void setUpAll() {
                ProductRepository productRepository = new ProductRepository();
                productRepository.deleteAll();
        }

        @AfterAll
        static void tearDownAll() {
                ProductRepository productRepository = new ProductRepository();
                productRepository.deleteAll();
        }

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
        void createAndEdit(ChromeDriver driver) {
                driver.get(baseUrl);

                WebElement name = driver.findElement(By.name("productName"));
                name.sendKeys("Product Edit");

                WebElement quantity = driver.findElement(By.name("productQuantity"));
                quantity.sendKeys("100");

                WebElement submit = driver.findElement(By.id("submitButton"));
                submit.click();

                // On the table, look for the first row and third columnn (go to edit)
                WebElement editButton = driver.findElement(By.xpath("//table/tbody/tr[1]/td[3]/a"));
                editButton.click();

                WebElement productName = driver.findElement(By.name("productName"));
                productName.clear();
                productName.sendKeys("Product 2");

                WebElement productQuantity = driver.findElement(By.name("productQuantity"));
                productQuantity.clear();
                productQuantity.sendKeys("200");

                WebElement submitEdit = driver.findElement(By.id("submitButton"));
                submitEdit.click();

                // On the table, look for first row and first column
                WebElement editedProductName = driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]"));
                assertEquals("Product 2", editedProductName.getText());

                // On the table, look for first row and second column
                WebElement editedProductQuantity = driver.findElement(By.xpath("//table/tbody/tr[1]/td[2]"));
                assertEquals("200", editedProductQuantity.getText());
        }
}
