package com.productservice.ProductService.Repository;

import com.productservice.ProductService.models.Price;
import com.productservice.ProductService.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findDistinctByDescription(String description);
    Product findByTitle(String title);

    List<Product> findAllByPrice_ValueBetween(int x, int y);

    List<Product> findAllByPrice_valueGreaterThanEqual(int x);


}