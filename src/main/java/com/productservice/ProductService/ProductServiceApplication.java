package com.productservice.ProductService;


import com.productservice.ProductService.Repository.CategoryRepository;
import com.productservice.ProductService.Repository.PriceRepository;
import com.productservice.ProductService.Repository.ProductRepository;
import com.productservice.ProductService.models.Category;
import com.productservice.ProductService.models.Price;
import com.productservice.ProductService.models.Product;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class ProductServiceApplication  implements CommandLineRunner {

	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;
	private final PriceRepository priceRepository;

	public ProductServiceApplication(CategoryRepository categoryRepository,
									 ProductRepository productRepository,
									 PriceRepository priceRepository)
	{

		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
		this.priceRepository = priceRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//
		Category category = new Category();
		category.setName("Apple Devices");
		Category savedCategory = categoryRepository.save(category);
//
//		List<Category> categories = categoryRepository.findAll();
//		UUID categoryId = categories.get(0).getId();
//		Optional<Category> category = categoryRepository.findById(categoryId);

		Price price1 = new Price();
		price1.setCurrency("INR");
		price1.setValue(800000);
//
		Product product = new Product();
		product.setTitle("Iphone 12 pro");
		product.setDescription("Best iphone before 15!");
		product.setCategory(savedCategory);
		product.setPrice(price1);
//
		productRepository.save(product);

//		Price price2 = new Price();
//		price2.setCurrency("INR");
//		price2.setValue(50000);
//
//		Product product1 = new Product();
//		product1.setTitle("Iphone 15 pro");
//		product1.setDescription("Best iphone in the world!");
//		product1.setCategory(category.get());
//		product1.setPrice(price2);
//		productRepository.save(product1);
//
//
//		Price price3 = new Price();
//		price3.setCurrency("INR");
//		price3.setValue(30000);
//
//		Product product2 = new Product();
//		product2.setTitle("Iphone 15 pro");
//		product2.setDescription("Best iphone in the world!");
//		product2.setCategory(category.get());
//		product2.setPrice(price3);
//
//		productRepository.save(product2);

//		List<Product> products = productRepository.findAll();
//		UUID priceId =  products.get(0).getId();
//
//		productRepository.deleteById(priceId);

List<Product> products = productRepository.findAllByPrice_valueGreaterThanEqual(30000);

	}
}
