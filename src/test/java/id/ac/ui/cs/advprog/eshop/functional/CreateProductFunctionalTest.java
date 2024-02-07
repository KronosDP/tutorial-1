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
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {

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
	void pageTitle_isCorrect(ChromeDriver driver) {
		driver.get(baseUrl);
		String pageTitle = driver.getTitle();
		assertEquals("Create New Product", pageTitle);
	}

	@Test
	void welcomeMessage_homepage_isCorrect(ChromeDriver driver) throws Exception {
		driver.get(baseUrl);
		String welcomeMessage = driver.findElement(By.tagName("h3")).getText();
		assertEquals("Create New Product", welcomeMessage);
	}

	@Test
	void createManyProducts(ChromeDriver driver) {
		Random random = new Random();
		int numberOfProducts = 100;
		for (int i = 0; i < numberOfProducts; i++) {
			driver.get(baseUrl);
			WebElement name = driver.findElement(By.name("productName"));
			String productName = "Product " + i;
			name.sendKeys(productName);

			WebElement quantity = driver.findElement(By.name("productQuantity"));
			int productQuantity = 1 + random.nextInt(100);
			quantity.sendKeys(String.valueOf(productQuantity));

			WebElement submit = driver.findElement(By.id("submitButton"));
			submit.click();
		}
		List<WebElement> productList = driver.findElements(By.tagName("tr"));
		assertEquals(2 + numberOfProducts, productList.size());
	}

}
