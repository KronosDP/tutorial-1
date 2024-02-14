package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import id.ac.ui.cs.advprog.eshop.controller.ProductController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EshopApplicationTests {

	@Autowired
	private ProductController controller;

	@Test
	void contextLoads() {
		String[] args = { "tes" };
		EshopApplication.main(args);
		assertThat(controller).isNotNull();
	}
}
