package com.productservice.ProductService;


import com.productservice.ProductService.Repository.CategoryRepository;
import com.productservice.ProductService.Repository.PriceRepository;
import com.productservice.ProductService.Repository.ProductRepository;
import com.productservice.ProductService.models.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
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

//		Category category = new Category();
//		category.setName("Apple Devices");
//		Category savedCategory = categoryRepository.save(category);

//		List<Category> categories = categoryRepository.findAll();
//		UUID categoryId = categories.get(0).getId();
//		Optional<Category> category = categoryRepository.findById(categoryId);

//		Price price = new Price();
//		price.setCurrency("INR");
//		price.setValue(100000);
//		Price savedPrice = priceRepository.save(price);

//		Product product = new Product();
//		product.setTitle("Iphone 15 pro");
//		product.setDescription("Best iphone in the world!");
//		product.setCategory(savedCategory);
//		product.setPrice(price);
//
//		productRepository.save(product);

		List<Product> products = productRepository.findAll();
		UUID priceId =  products.get(0).getId();

		productRepository.deleteById(priceId);

	}
}
