package com.relayr.productcomparison;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relayr.productcomparison.model.Product;
import com.relayr.productcomparison.repository.ProductMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class ProductComparisonApplication implements CommandLineRunner {

	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	ProductMongoRepository productMongoRepository;
	@Value("${product.json.path}")
	String jsonPath = "";

	private static final Logger logger = LoggerFactory.getLogger(ProductComparisonApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProductComparisonApplication.class, args);

	}

	/**
	 * 	Loads initial data into Embedded Mongo DB on application startup
	 * 	Data is loaded up from products.txt file
	 * @param  args command line arguments passed to the main method
	 */
	@Override
	public void run(String... args) throws Exception {
		logger.info("Adding initial setup products data to embedded DB");
		Resource resource = new ClassPathResource(jsonPath);
		InputStream inputStream = resource.getInputStream();
		byte[] byteData = FileCopyUtils.copyToByteArray(inputStream);
		String data = new String(byteData, StandardCharsets.UTF_8);
		Product product = objectMapper.readValue(data,Product.class);
		productMongoRepository.save(product);
		logger.info("Initial setup data has been successfully added");
	}
}
