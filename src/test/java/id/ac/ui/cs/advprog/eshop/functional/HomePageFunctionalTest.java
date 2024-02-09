package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class HomePageFunctionalTest {

        /*
         * The port number assigned to the running aplication dring test execution.
         * Set automatically during each test run by Spring Framework's test context.
         */
        @LocalServerPort
        private int serverPort;

        /*
         * The base URL for testing. Default to {@code http://localhost}.
         */
        @Value("${app.baseUrl:http://localhost}")
        private String testbaseUrl;
        private String baseUrl;

        @BeforeEach
        void setUp() {
                baseUrl = String.format("%s:%d", testbaseUrl, serverPort);
        }

        @Test
        void pageTitle_isCorrect(ChromeDriver driver) {
                driver.get(baseUrl);
                String pageTitle = driver.getTitle();
                assertEquals("Eshop Simulator", pageTitle);
        }

        @Test
        void welcomeMessage_homepage_isCorrect(ChromeDriver driver) throws Exception {
                driver.get(baseUrl);
                String welcomeMessage = driver.findElement(By.tagName("h3")).getText();
                assertEquals("Welcome To Eshop Simulator", welcomeMessage);
        }
}
